package com.example.firstgroupassigment.module;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firstgroupassigment.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;

public class Location extends AppCompatActivity {

    LocationCallback locationCallback;

    TextView tv_lat, tv_lon, tv_address;
    Switch sw_locationupdates ;
    //This is Google Location Service API, this is the heart of our application.
    FusedLocationProviderClient fusedLocationProviderClient;
    // this is configuration class that tells FusedLocationProviderClient how to work
    LocationRequest locationRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        Intent intent=getIntent();
        tv_lat = findViewById(R.id.tv_lat);
        tv_lon = findViewById(R.id.tv_lon);

        tv_address = findViewById(R.id.tv_address);
        sw_locationupdates = findViewById(R.id.sw_locationsupdates);

        locationRequest = LocationRequest.create();

        locationRequest.setInterval(30 * 1000);
        locationRequest.setFastestInterval(5 * 1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);

        //this event is triggered whenever the update interval in met
        locationCallback = new LocationCallback() {

            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                updateUI(locationResult.getLastLocation());
            }
        };
        updateGPS();
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == 99) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                updateGPS();
            } else {
                Toast.makeText(this, "this app requires permissions to work properly", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    private void updateGPS() {
        //get permissions from the user to track gps
        //get current location from fused client
        //update UI
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(Location.this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<android.location.Location>() {
                @Override
                public void onSuccess(android.location.Location location) {
                    updateUI(location);
                }
            });
        } else {
            //permission not granted, we will ask for it
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 99);
            }
        }
    }

    private void updateUI(android.location.Location location) {
        tv_lat.setText(String.valueOf(location.getLatitude()));
        tv_lon.setText(String.valueOf(location.getLongitude()));



        Geocoder geocoder = new Geocoder(this);
        try {
            List<Address> address = geocoder.getFromLocation(location.getLatitude(),
                    location.getLongitude(), 1);
            tv_address.setText(address.get(0).getAddressLine(0));
        } catch (Exception e) {
            tv_address.setText("Cannot get Street Address!");
        }

    }

    public void sw_updates_onclick(View view) {
        if (sw_locationupdates.isChecked()) {
            startLocationUpdates();
        } else {
            stopLocationUpdates();
        }
    }

    private void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationProviderClient.requestLocationUpdates(locationRequest,
                locationCallback, null);
        updateGPS();
    }
    private void stopLocationUpdates(){
        tv_lat.setText("Not Tracking Location");
        tv_lon.setText("Not Tracking Location");
        tv_address.setText("Not Tracking Location");
        fusedLocationProviderClient.removeLocationUpdates(locationCallback);

    }

    public void Buyclicked(View view) {
        Toast.makeText(Location.this,"Thank you for Buying",Toast.LENGTH_LONG).show();
    }
}