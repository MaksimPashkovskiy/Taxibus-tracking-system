package com.example.taxibus_tracking_system;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.taxibus_tracking_system.ui.info.InfoFragment;
import com.example.taxibus_tracking_system.ui.map.MapFragment;
import com.example.taxibus_tracking_system.ui.routes.RoutesFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements RoutesFragment.OnStatusSendListener {

    private static final String TAG = "MainActivity";

//    private SharedPreferencesConfig preferencesConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_map, R.id.navigation_routes, R.id.navigation_info)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);*/

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

    @Override
    public void onStatusSend(Boolean status) {
        /*InfoFragment infoFragment = new InfoFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean("status", status);
        infoFragment.setArguments(bundle);
*/
        /*FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, infoFragment, null);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();*/
    }
}