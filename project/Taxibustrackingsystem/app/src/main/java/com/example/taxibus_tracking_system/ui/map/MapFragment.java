package com.example.taxibus_tracking_system.ui.map;

import android.Manifest;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.taxibus_tracking_system.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.concurrent.Executor;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    private static final String TAG = MapFragment.class.getSimpleName();
    private MapViewModel mapViewModel;
    private GoogleMap mMap;
    private CameraPosition mCameraPosition;
    private UiSettings mUiSettings;

    // The entry point to the Fused Location Provider.
    private FusedLocationProviderClient mFusedLocationProviderClient;

    // The geographical location where the device is currently located. That is, the last-known
    // location retrieved by the Fused Location Provider.
    private Location mLastKnownLocation;

    // Keys for storing activity state.
    private static final String KEY_CAMERA_POSITION = "camera_position";
    private static final String KEY_LOCATION = "location";

    // A default location (Odessa, Ukraine) and default zoom to use when location permission is
    // not granted.
    private final LatLng mDefaultLocation = new LatLng(46.4775, 30.7326);
    private static final int DEFAULT_ZOOM = 15;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private boolean mLocationPermissionGranted;

    public MapFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);  //use SuppoprtMapFragment for using in fragment instead of activity  MapFragment = activity   SupportMapFragment = fragment
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {

                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                mMap.getUiSettings().setZoomControlsEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
                mMap.getUiSettings().setCompassEnabled(true);

                CameraPosition googlePlex = CameraPosition.builder()
                        .target(new LatLng(46.4775, 30.7326))
                        .zoom(12)
                        .bearing(0)
                        .build();

                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 10, null);

                // Prompt the user for permission.
                getLocationPermission();

                // Turn on the My Location layer and the related control on the map.
                updateLocationUI();

                // Get the current location of the device and set the position of the map.
                getDeviceLocation();

                Polyline TaxibusRouteNum4 = mMap.addPolyline(new PolylineOptions()
                        .clickable(true)
                        .color(0xFF06B80E)
                        .add(
                                new LatLng(46.462774, 30.741011),
                                new LatLng(46.462140, 30.750177),
                                new LatLng(46.458404, 30.749777),
                                new LatLng(46.456952, 30.750734),
                                new LatLng(46.456929, 30.750476),
                                new LatLng(46.454510, 30.744127),
                                new LatLng(46.445317, 30.742730),
                                new LatLng(46.445110, 30.742145),
                                new LatLng(46.444871, 30.741987),
                                new LatLng(46.437887, 30.745578),
                                new LatLng(46.436386, 30.741482),
                                new LatLng(46.437375, 30.730792),
                                new LatLng(46.430399, 30.729401),
                                new LatLng(46.430273, 30.729077),
                                new LatLng(46.429954, 30.727740),
                                new LatLng(46.432360, 30.724596),
                                new LatLng(46.434975, 30.721103),
                                new LatLng(46.441556, 30.705968),
                                new LatLng(46.442224, 30.705623),
                                new LatLng(46.443031, 30.703697),
                                new LatLng(46.443010, 30.703110),
                                new LatLng(46.443042, 30.702807),
                                new LatLng(46.451712, 30.683057),
                                new LatLng(46.456786, 30.682264),
                                new LatLng(46.458027, 30.684525),
                                new LatLng(46.458208, 30.684526),
                                new LatLng(46.458557, 30.684020),
                                new LatLng(46.456980, 30.681754),
                                new LatLng(46.452389, 30.673891),
                                new LatLng(46.451070, 30.668670),
                                new LatLng(46.450611, 30.662513),
                                new LatLng(46.450710, 30.659510),
                                new LatLng(46.450967, 30.656341),
                                new LatLng(46.450452, 30.650652),
                                new LatLng(46.448635, 30.646283),
                                new LatLng(46.448351, 30.645417),
                                new LatLng(46.445131, 30.633854),
                                new LatLng(46.445031, 30.633479),
                                new LatLng(46.444887, 30.633511),
                                new LatLng(46.443986, 30.633724),
                                new LatLng(46.443557, 30.634400),
                                new LatLng(46.443760, 30.634630),
                                new LatLng(46.443879, 30.634212),
                                new LatLng(46.444887, 30.633511)
                        ));

            }
        });
        return view;
    }

    private void getDeviceLocation() {
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */

        try {
            if (mLocationPermissionGranted) {
                Task<Location> locationResult = mFusedLocationProviderClient.getLastLocation();
                locationResult.addOnCompleteListener((Executor) this, new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful()) {
                            // Set the map's camera position to the current location of the device.
                            mLastKnownLocation = task.getResult();
                            if (mLastKnownLocation != null) {
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mLastKnownLocation.getLatitude(), mLastKnownLocation.getLongitude()), DEFAULT_ZOOM));
                            }
                        } else {
                            Log.d(TAG, "Current location is null. Using defaults.");
                            Log.e(TAG, "Exception: %s", task.getException());
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mDefaultLocation, DEFAULT_ZOOM));
                            mMap.getUiSettings().setMyLocationButtonEnabled(false);
                        }
                    }
                });
            }
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    private void getLocationPermission() {
        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
    }

    /**
     * Updates the map's UI settings based on whether the user has granted location permission.
     */

    private void updateLocationUI() {
        if (mMap == null) {
            return;
        }
        try {
            if (mLocationPermissionGranted) {
                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(true);
            } else {
                mMap.setMyLocationEnabled(false);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);
                mLastKnownLocation = null;
                getLocationPermission();
            }
        } catch (SecurityException e) {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        //Do your stuff here
    }


}
