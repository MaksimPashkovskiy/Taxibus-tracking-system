package com.example.taxibus_tracking_system.ui.map;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.taxibus_tracking_system.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;

    private final LatLng mDefaultLocation = new LatLng(46.4775, 30.7326);
    private static final int DEFAULT_ZOOM = 12;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;

    public MapFragment() {
        // Required empty public constructor
    }

    public static MapFragment newInstance() {
        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
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
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.getUiSettings().setZoomControlsEnabled(true);

        CameraPosition cityOdessa = CameraPosition.builder()
                .target(new LatLng(mDefaultLocation.latitude, mDefaultLocation.longitude))
                .zoom(DEFAULT_ZOOM)
                .bearing(0)
                .build();

        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cityOdessa), 10, null);

        getPolylineRouteNum4();

        // Prompt the user for permission.
        getLocationPermission();
    }

    private void getPolylineRouteNum4() {

        Polyline TaxiBusRouteNum4 = mMap.addPolyline(new PolylineOptions()
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


    private void getLocationPermission() {
        requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        if (ContextCompat.checkSelfPermission(this.requireActivity(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);

            // FIXME: Fix PERMISSION_GRANTED. Make more security & add LocationEnabled in a first frame
        }
    }


    /*@SuppressLint("ShowToast")
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Toast.makeText(this.getContext(), "onAttach", Toast.LENGTH_LONG).show();
    }

    @SuppressLint("ShowToast")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Toast.makeText(this.getContext(), "onActivityCreated", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStart() {
        super.onStart();
        Toast.makeText(this.getContext(), "onStart", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        Toast.makeText(this.getContext(), "onResume", Toast.LENGTH_LONG).show();
    }

    @SuppressLint("ShowToast")
    @Override
    public void onPause() {
        super.onPause();
        Toast.makeText(this.getActivity(), "onPause", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStop() {
        super.onStop();
        Toast.makeText(this.getContext(), "onStop", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Toast.makeText(this.getContext(), "onDestroyView", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this.getContext(), "onDestroy", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Toast.makeText(this.getContext(), "onDetach", Toast.LENGTH_LONG).show();
    }*/
}
