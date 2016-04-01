package xyz.iiemyewrs.www.technica.activities;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import xyz.iiemyewrs.www.technica.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            onBackPressed();
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
        mMap.setBuildingsEnabled(true);
        try {
            mMap.setMyLocationEnabled(true);
        }catch (SecurityException e){
            Log.e("Maps",""+e);
        }
        mMap.setIndoorEnabled(true);
        LatLng nitDelhi = new LatLng(28.8430227, 77.1051845);
        CameraPosition target= CameraPosition.builder().target(nitDelhi).zoom((float)17.6).bearing(70).tilt(30).build();

        MarkerOptions defaultMarker =new MarkerOptions().position(nitDelhi).title("National Institute of Technology, New Delhi").snippet("Terra Technica'16").icon(BitmapDescriptorFactory.fromResource(R.drawable.nitmarker));
        mMap.addMarker(defaultMarker).showInfoWindow();
        mMap.addMarker(new MarkerOptions().position(new LatLng(28.842693, 77.105865)).title("IAMR Hostel").icon(BitmapDescriptorFactory.fromResource(R.drawable.commercialplaces)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(28.842971, 77.104010)).title("Girls Hostel").icon(BitmapDescriptorFactory.fromResource(R.drawable.hotels)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(28.842880, 77.104850)).title("Library").icon(BitmapDescriptorFactory.fromResource(R.drawable.magazines)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(28.842859, 77.104893)).title("Computer Center").icon(BitmapDescriptorFactory.fromResource(R.drawable.computers)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(28.842252, 77.105158)).title("Cafe").icon(BitmapDescriptorFactory.fromResource(R.drawable.cafe)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(28.842581, 77.103792)).title("BasketBall Court").icon(BitmapDescriptorFactory.fromResource(R.drawable.sports)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(28.842409, 77.105404)).title("Parking").icon(BitmapDescriptorFactory.fromResource(R.drawable.automotive)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(28.843755, 77.106214)).title("Guest House").icon(BitmapDescriptorFactory.fromResource(R.drawable.government)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(28.843233, 77.105366)).title("Main Stage").icon(BitmapDescriptorFactory.fromResource(R.drawable.concerts)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(28.842334, 77.104082)).title("VolleyBall Court").icon(BitmapDescriptorFactory.fromResource(R.drawable.playgrounds)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(28.842604, 77.104362)).title("Academic Block").icon(BitmapDescriptorFactory.fromResource(R.drawable.schools)));
        mMap.addMarker(new MarkerOptions().position(new LatLng(28.843362, 77.105213)).title("Auditorium").icon(BitmapDescriptorFactory.fromResource(R.drawable.event)));

        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            @Override
            public View getInfoWindow(Marker arg0) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                Context mContext = getApplicationContext();
                LinearLayout info = new LinearLayout(mContext);
                info.setOrientation(LinearLayout.VERTICAL);

                TextView title = new TextView(mContext);
                title.setTextColor(Color.BLACK);
                title.setGravity(Gravity.CENTER);
                title.setTypeface(null, Typeface.BOLD);
                title.setText(marker.getTitle());

                TextView snippet = new TextView(mContext);
                snippet.setTextColor(Color.GRAY);
                snippet.setText(marker.getSnippet());
                snippet.setGravity(Gravity.CENTER);

                info.addView(title);
                info.addView(snippet);

                return info;
            }
        });

        mMap.addPolyline(new PolylineOptions().geodesic(true)
                .add(new LatLng(28.841669, 77.103909))
                .add(new LatLng(28.843347, 77.103217))
                .add(new LatLng(28.844424, 77.106512))
                .add(new LatLng(28.842789, 77.107219))
                .add(new LatLng(28.841669, 77.103909))
                .color(R.color.primary));

        mMap.addPolyline(new PolylineOptions().geodesic(true)
                .add(new LatLng(28.842884, 77.104147))
                .add(new LatLng(28.842788, 77.104222))
                .add(new LatLng(28.842628, 77.104598))
                .add(new LatLng(28.842626, 77.104737))
                .add(new LatLng(28.842689, 77.105008))
                .add(new LatLng(28.842590, 77.105038))
                .add(new LatLng(28.842531, 77.104826))
                .add(new LatLng(28.842498, 77.104829))
                .add(new LatLng(28.842479, 77.104655))
                .add(new LatLng(28.842505, 77.104440))
                .add(new LatLng(28.842618, 77.104177))
                .add(new LatLng(28.842797, 77.104008))
                .add(new LatLng(28.842884, 77.104147))
                .color(R.color.primary));

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(target));
    }

}
