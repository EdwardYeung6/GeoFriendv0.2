package com.geofriend.geofriend;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class LandMark {

    //GLOBALS
    private int mID;

    private String mName;

    private LatLng mLocation;
    private double mLatitude;
    private double mLongitude;

    private String mDesc;
    //GLOBALS

    public LandMark(int mID, String mName, double mLatitude, double mLongitude, String mDesc) {

        mID = this.mID;
        mName = this.mName;

        this.mLocation = new LatLng(mLatitude, mLongitude);
        mLatitude = this.mLatitude;
        mLongitude = this.mLongitude;

        mDesc = this.mDesc;

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
        mName = this.mName;
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
        mDesc = this.mDesc;
    }

    public int getID() {
        return mID;
    }

    public void setID(int mID) {
        mID = this.mID;
    }


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
    public void updateLandMark(int mID, String mName, double mLatitude, double mLongitude, String mDesc){
        LandMark old = new LandMark(mID, mName, mLatitude, mLatitude, mDesc);
        setName(mName);
        setDesc(mDesc);
        setLocation(mLocation.latitude, mLocation.longitude);
        setID(mID);

        //replace the old object with the new object in database

    }

}
