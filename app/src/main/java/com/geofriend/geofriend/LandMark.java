package com.geofriend.geofriend;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class LandMark {

    //GLOBALS
   // private int mID;

    private String mName;

    private LatLng mLocation;
    private double mLatitude;
    private double mLongitude;

    private String mDesc;
    //GLOBALS

    public LandMark(String mName, double mLatitude, double mLongitude, String mDesc) {

        //this.mID = mID;
        this.mName = mName;

        this.mLocation = new LatLng(mLatitude, mLongitude);
        this.mLatitude = mLatitude;
        this.mLongitude = mLongitude;

        this.mDesc = mDesc;

    }

    //copy constructor using in modify landmark
//    public LandMark(LandMark old) {
//        mName = old.getName();
//        mLocation = old.getLocation();
//        mDesc = old.getDesc();
//        mID = old.getID();
//    }



    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public LatLng getLocation() {
        return mLocation;
    }

    public void setLocation(double mLatitude, double mLongitude) {
        this.mLocation = new LatLng(mLatitude, mLongitude);
    }

    public String getDesc() {
        return mDesc;
    }

    public void setDesc(String mDesc) {
        this.mDesc = mDesc;
    }

  /*  public int getID() {
        return mID;
    }

    public void setID(int mID) {
        this.mID = mID;
    }*/

    public double getmLatitude(){return mLatitude;}

    public void setmLatitude(double b){mLatitude=b;}

    public double getmLongitude(){return mLongitude;}

    public void setmLongitude(double b){mLongitude=b;}


    //Working on Database

    //search LandMark by code in Database then assign the data in the object
    public void searchByID(int ID){

    }

    //search LandMark by code in Database then assign the data in the object
    public void searchByLocation(LatLng searchLocation){

    }

    //search LandMark by Name in Database then assign the data in the object
    public void searchByName(String name){

    }

    //remove from database
    public void removeLandMark(){

    }

    //add this object to database(use as create new object)
    public void addLandMark(){

    }

    //update object to database(use as modify exist object)
    public void updateLandMark(String mName, double mLatitude, double mLongitude, String mDesc){
        LandMark old = new LandMark( mName, mLatitude, mLatitude, mDesc);
        setName(mName);
        setDesc(mDesc);
        setLocation(mLocation.latitude, mLocation.longitude);
        //setID(mID);

        //replace the old object with the new object in database

    }

}
