package com.androidapplication.amine.docteurapp;

import android.app.ProgressDialog;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap,mMap2;
    private static final String TAG_Latitude = "longitude";
    private static final String TAG_Longitude = "latitude";
    private static final String TAG_NOM = "nom_prenom";
    public String txtlatitude, txtlongitude, txtnom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        txtlatitude = (getIntent().getStringExtra(TAG_Latitude));
        txtlongitude = (getIntent().getStringExtra(TAG_Longitude));
        txtnom = (getIntent().getStringExtra(TAG_NOM));

        Toast t= Toast.makeText(getApplicationContext(),"Position : " + txtlatitude + " " + txtlongitude ,Toast.LENGTH_SHORT);
        t.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 320);
        t.show();

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        double latitude = Double.parseDouble(txtlatitude);
        double longitude = Double.parseDouble(txtlongitude);
        // Add a marker in Sydney and move the camera
        LatLng PosDocteur = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(PosDocteur).title("Docteur : " + txtnom));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(PosDocteur));

    }

    /*@Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap2 = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        LatLng xxx = new LatLng(-38, 160);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        mMap2.addMarker(new MarkerOptions().position(xxx).title("hello"));
        mMap2.moveCamera(CameraUpdateFactory.newLatLng(xxx));
    }*/

}
