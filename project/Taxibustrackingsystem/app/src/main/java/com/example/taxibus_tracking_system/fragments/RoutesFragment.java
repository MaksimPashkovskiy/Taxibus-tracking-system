package com.example.taxibus_tracking_system.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.taxibus_tracking_system.R;
import com.example.taxibus_tracking_system.SharedPreferencesConfig;

public class RoutesFragment extends Fragment {

    public static final String TAG = "RoutesFragment";
    private SharedPreferencesConfig preferencesConfig;

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_routes, container, false);

        preferencesConfig = new SharedPreferencesConfig(requireActivity());
        Switch switchRouteNum4 = view.findViewById(R.id.switchRouteNum4);
        switchRouteNum4.setChecked(preferencesConfig.readRouteStatus());

        switchRouteNum4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    preferencesConfig.writeRouteStatus(true);
                } else {
                    preferencesConfig.writeRouteStatus(false);
                }
            }
        });

        return view;
    }
}
