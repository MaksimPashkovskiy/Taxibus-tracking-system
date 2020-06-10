package com.example.taxibus_tracking_system;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.taxibus_tracking_system.fragments.InfoFragment;
import com.example.taxibus_tracking_system.fragments.MapFragment;
import com.example.taxibus_tracking_system.fragments.RoutesFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(navigationViewListener);
        if (findViewById(R.id.frameLayout) != null) {
            if (savedInstanceState != null) {
                return;
            }
            MapFragment mapFragment = new MapFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.frameLayout, mapFragment, null).commit();
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationViewListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()){
                case R.id.navigation_map:
                    MapFragment mapFragment = new MapFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, mapFragment, MapFragment.TAG).commit();
                    return true;
                case R.id.navigation_routes:
                    RoutesFragment routesFragment = new RoutesFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, routesFragment, RoutesFragment.TAG).commit();
                    return true;
                case R.id.navigation_info:
                    InfoFragment infoFragment = new InfoFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, infoFragment, InfoFragment.TAG).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        for (Fragment fragment : fragments) {
            fragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

}