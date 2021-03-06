package com.geofriend.geofriend;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.geofriend.geofriend.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    //DECLARE AUTHORIZATION
    private FirebaseAuth mAuth;

    //DECLARE BINDING
    private ActivityLoginBinding mBinding;

    private String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Initialize Firebase Authentication
        mAuth = FirebaseAuth.getInstance();

        //INSTANTIATING DATABINDING
        //Loads activity_login.xml Layout file
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        //SETTING ON CLICK LISTENERS
        mBinding.emailSignInButton.setOnClickListener(this);
        mBinding.emailCreateAccountButton.setOnClickListener(this);
        mBinding.signOutButton.setOnClickListener(this);
        mBinding.exploreButton.setOnClickListener(this);
        mBinding.geofenceButton.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        //Loads User information into the UI
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = mBinding.fieldEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mBinding.fieldEmail.setError("Required.");
            valid = false;
        } else {
            mBinding.fieldEmail.setError(null);
        }

        String password = mBinding.fieldPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mBinding.fieldPassword.setError("Required.");
            valid = false;
        } else {
            mBinding.fieldPassword.setError(null);
        }

        return valid;
    }

    private void updateUI(FirebaseUser user) {
        //hideProgressBar();
        if (user != null) {
            mBinding.status.setText(R.string.signed_in);
            mBinding.detail.setText(R.string.app_name);

            mBinding.emailPasswordButtons.setVisibility(View.GONE);
            mBinding.emailPasswordFields.setVisibility(View.GONE);
            mBinding.signedInButtons.setVisibility(View.VISIBLE);

        } else {
            mBinding.status.setText(R.string.signed_out);
            mBinding.detail.setText(null);

            mBinding.emailPasswordButtons.setVisibility(View.VISIBLE);
            mBinding.emailPasswordFields.setVisibility(View.VISIBLE);
            mBinding.signedInButtons.setVisibility(View.GONE);
        }
    }

    private void sendEmailVerification() {
        // Disable button
        mBinding.exploreButton.setEnabled(false);

        // Send verification email
        // [START send_email_verification]
        final FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            user.sendEmailVerification()
                    .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            // [START_EXCLUDE]
                            // Re-enable button
                            mBinding.exploreButton.setEnabled(true);

                            if (task.isSuccessful()) {

                                Toast.makeText(LoginActivity.this, "Verification email sent to " + user.getEmail(), Toast.LENGTH_SHORT).show();

                            } else {

                                Log.e("TAG", "sendEmailVerification", task.getException());
                                Toast.makeText(LoginActivity.this, "Failed to send verification email.", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
        }
        else
            Toast.makeText(LoginActivity.this, "There is no user to send verification to.", Toast.LENGTH_LONG).show();
    }

    private void createAccount(String email, String password) {
        //showProgressBar();

        //PULLS EMAIL AND PASSWORD VALUES FROM THE CONTROLS
        EditText et = findViewById(R.id.fieldEmail);
        email = et.getText().toString();

        EditText et1 = findViewById(R.id.fieldPassword);
        password = et1.getText().toString();

        // CREATE USER PROCESS
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "createUserWithEmail:success");

                            Toast.makeText(LoginActivity.this, "User Creation Successful. Firebase Updated.", Toast.LENGTH_LONG).show();

                            FirebaseUser user = mAuth.getCurrentUser();
                            sendEmailVerification();

                            //updateUI(user);
                        } else {

                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "createUserWithEmail:failure", task.getException());

                            Toast.makeText(LoginActivity.this, "User Creation Failed.", Toast.LENGTH_LONG).show();

                            updateUI(null);
                        }
                    }
                });
    }

    private void signIn(String email, String password) {
        // [START sign_in_with_email]

        EditText et = findViewById(R.id.fieldEmail);
        email = et.getText().toString();

        EditText et1 = findViewById(R.id.fieldPassword);
        password = et1.getText().toString();


        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful() && mAuth.getCurrentUser().isEmailVerified()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithEmail:success");

                            //Toast.makeText(LoginActivity.this, "Authentication Successful. Signed in.", Toast.LENGTH_LONG).show();

                            updateUI(mAuth.getCurrentUser());

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "signInWithEmail:failure", task.getException());

                            //Toast.makeText(LoginActivity.this, "Authentication failed.", Toast.LENGTH_LONG).show();

                            updateUI(null);

                        }
                        if (!task.isSuccessful()) {
                            mBinding.status.setText(R.string.auth_failed);
                        }
                    }
                });
        // [END sign_in_with_email]
    }

    private void signOut() {
        mAuth.signOut();
        updateUI(null);
    }

    private void reload() {
        mAuth.getCurrentUser().reload().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    updateUI(mAuth.getCurrentUser());
                    Toast.makeText(LoginActivity.this,
                            "Reload successful!",
                            Toast.LENGTH_LONG).show();
                } else {
                    Log.e("TAG", "reload", task.getException());
                    Toast.makeText(LoginActivity.this,
                            "Failed to reload user.",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.emailCreateAccountButton) {
            email = mBinding.fieldEmail.getText().toString();
            password = mBinding.fieldPassword.getText().toString();
            createAccount(email, password);
        } else if (i == R.id.emailSignInButton) {
            email = mBinding.fieldEmail.getText().toString();
            password = mBinding.fieldPassword.getText().toString();
            signIn(email, password);
        } else if (i == R.id.signOutButton) {
            signOut();
        } else if (i == R.id.exploreButton) {
            Intent toExplore = new Intent(LoginActivity.this, MapsActivity.class);
            startActivity(toExplore);
        } else if (i == R.id.geofenceButton){
            Intent toGeofence = new Intent (this, CurrentLocation.class);
            startActivity(toGeofence);
        }
    }
}