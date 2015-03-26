package com.example.samford.shoppingwithfriends;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends Activity implements OnMapReadyCallback {

    private static LatLng loc;

    /**
     * Creates the MapActivity
     * @param savedInstanceState the saved state of the previous runtime
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Logic for when the map loads
     * @param map the map that is loaded
     */
    @Override
    public void onMapReady(final GoogleMap map) {
        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng point) {
                loc = point;
                map.addMarker(new MarkerOptions().position(point));
                finish();
            }
        });
//        LatLng sydney = new LatLng(-33.867, 151.206);
//
//        map.setMyLocationEnabled(true);
//        map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));
//
//        map.addMarker(new MarkerOptions()
//                .title("Sydney")
//                .snippet("The most populous city in Australia.")
//                .position(sydney));
    }

    /**
     * Initialize the contents of the Activity's standard options menu.
     *
     * @param menu The options menu in which items are placed.
     * @return return true for the menu to be displayed;
     *         return false means it will not be shown.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_map, menu);
        return true;
    }

    /**
     * gets the lat-long
     * @return the lat-long
     */
    public static LatLng getLoc() {
        return loc;
    }

    /**
     * sets the lat-long
     * @param loc lat-long
     */
    public static void setLoc(LatLng loc) {
        MapActivity.loc = loc;
    }
}


