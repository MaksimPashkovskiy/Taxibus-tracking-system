package com.example.taxibus_tracking_system.fragments;

import android.Manifest;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.taxibus_tracking_system.DBHelper;
import com.example.taxibus_tracking_system.DoubleArrayEvaluator;
import com.example.taxibus_tracking_system.R;
import com.example.taxibus_tracking_system.SharedPreferencesConfig;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    public static final String TAG = "MapFragment";

    private final LatLng mDefaultLocation = new LatLng(46.4775, 30.7326);
    private static final int DEFAULT_ZOOM = 12;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;

    public MapFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        @SuppressLint("InflateParams") View rootView = inflater.inflate(R.layout.fragment_map, null, false);
        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
        return rootView;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.getUiSettings().setZoomControlsEnabled(true);

        CameraPosition cityOdessa = CameraPosition.builder()
                .target(new LatLng(mDefaultLocation.latitude, mDefaultLocation.longitude))
                .zoom(DEFAULT_ZOOM)
                .bearing(0)
                .build();

        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cityOdessa), 10, null);

        SharedPreferencesConfig preferencesConfig = new SharedPreferencesConfig(requireActivity());
        if (preferencesConfig.readRouteStatus()) {
            getRouteFromDataBase(googleMap);
            LatLng oldLatLng = new LatLng(46.434964, 30.720885);
            LatLng newLatLng = new LatLng(46.451558, 30.683085);
            Marker marker = googleMap.addMarker(new MarkerOptions().position(oldLatLng).title("Маршрут №4"));
            setMarkerMovement(marker, newLatLng);
        }
        getLocationPermission(googleMap);
    }

    private void setMarkerMovement(final Marker marker, LatLng newLatLng) {
        double[] startValues = new double[]{marker.getPosition().latitude, marker.getPosition().longitude};
        double[] endValues = new double[]{newLatLng.latitude, newLatLng.longitude};
        ValueAnimator latLngAnimator = ValueAnimator.ofObject(new DoubleArrayEvaluator(), startValues, endValues);
        latLngAnimator.setDuration(800000);
        latLngAnimator.setInterpolator(new DecelerateInterpolator());
        latLngAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                double[] animatedValue = (double[]) animation.getAnimatedValue();
                marker.setPosition(new LatLng(animatedValue[0], animatedValue[1]));
            }
        });
        latLngAnimator.start();
    }


    private void getLocationPermission(GoogleMap map) {
        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        if (ContextCompat.checkSelfPermission(this.requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            map.setMyLocationEnabled(true);
            // FIXME: Fix PERMISSION_GRANTED. Make more security & add LocationEnabled in a first frame
        }
    }

    private void getRouteFromDataBase(GoogleMap map) {

        PolylineOptions routeOptions = new PolylineOptions();

        DBHelper mDBHelper = new DBHelper(this.getContext());
        SQLiteDatabase mDb;
        try {
            mDBHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }

        mDb = mDBHelper.getWritableDatabase();
        String query = "SELECT lat, lng FROM coordinates WHERE route_num = '4'";

        Cursor cursor = mDb.rawQuery(query, null);
        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            routeOptions.add(new LatLng(cursor.getDouble(0), cursor.getDouble(1)));
            cursor.moveToNext();
        }
        cursor.close();

        routeOptions.clickable(true);
        routeOptions.color(0xFF06B80E);
        map.addPolyline(routeOptions);
    }
}
