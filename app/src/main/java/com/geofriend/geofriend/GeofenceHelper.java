package com.geofriend.geofriend;

import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.maps.model.LatLng;

public class GeofenceHelper extends ContextWrapper {
PendingIntent pendingIntent;
private static final String TAG="GeoFencingHelper";

    public GeofenceHelper(Context base) {
        super(base);
    }

    public GeofencingRequest geofencingRequest(Geofence geofence){
        return null;
    }

    public Geofence getGeogfence(String ID, LatLng,float radius,int transitionTypes){
        return null;
    }


    public PendingIntent getPendingIntent(){
    return pendingIntent;
    }
}
