package com.example.samford.shoppingwithfriends;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ShowMapActivity extends Activity implements OnMapReadyCallback {

    /**
     * Creates the ShowMapActivity
     * @param savedInstanceState the saved state of the previous runtime
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_map);

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
        //get item info from the item detail activity
        Item selectedItem = ItemListActivity.getInstance().selectedItem;
        LatLng loc =
                new LatLng(selectedItem.getLocLat(), selectedItem.getLocLong());
        map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, 13));
        map.addMarker(new MarkerOptions()
                .title(selectedItem.getName())
                .snippet("Location")
                .position(loc));
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
}
