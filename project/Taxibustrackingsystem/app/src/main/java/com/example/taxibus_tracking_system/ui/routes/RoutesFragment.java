package com.example.taxibus_tracking_system.ui.routes;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.taxibus_tracking_system.R;

import static android.content.Context.MODE_PRIVATE;

public class RoutesFragment extends Fragment {

    private static final String SWITCH_ROUTE_NUM_4_TAG = "switchRouteNum4";
    private static final String SWITCH_ROUTE_NUM_5_TAG = "switchRouteNum5";
    private static final String SWITCH_ROUTE_NUM_6_TAG = "switchRouteNum6";

    private Switch switchRouteNum4;
    private Switch switchRouteNum5;
    private Switch switchRouteNum6;

    public static RoutesFragment newInstance() {
        RoutesFragment fragment = new RoutesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_routes, container, false);

        switchRouteNum4 = rootView.findViewById(R.id.switchRouteNum4);
        switchRouteNum5 = rootView.findViewById(R.id.switchRouteNum5);
        switchRouteNum6 = rootView.findViewById(R.id.switchRouteNum6);

        SharedPreferences sharedPreferencesRouteNum4 = this.getActivity().getSharedPreferences(SWITCH_ROUTE_NUM_4_TAG, MODE_PRIVATE);
        final SharedPreferences.Editor editorRouteNum4 = sharedPreferencesRouteNum4.edit();
        switchRouteNum4.setChecked(sharedPreferencesRouteNum4.getBoolean(SWITCH_ROUTE_NUM_4_TAG, false));
        switchRouteNum4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editorRouteNum4.putBoolean(SWITCH_ROUTE_NUM_4_TAG, true);
                } else {
                    editorRouteNum4.putBoolean(SWITCH_ROUTE_NUM_4_TAG, false);
                }
                editorRouteNum4.commit();
            }
        });

        SharedPreferences sharedPreferencesRouteNum5 = this.getActivity().getSharedPreferences(SWITCH_ROUTE_NUM_5_TAG, MODE_PRIVATE);
        final SharedPreferences.Editor editorRouteNum5 = sharedPreferencesRouteNum5.edit();
        switchRouteNum5.setChecked(sharedPreferencesRouteNum5.getBoolean(SWITCH_ROUTE_NUM_5_TAG, false));
        switchRouteNum5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editorRouteNum5.putBoolean(SWITCH_ROUTE_NUM_5_TAG, true);
                } else {
                    editorRouteNum5.putBoolean(SWITCH_ROUTE_NUM_5_TAG, false);
                }
                editorRouteNum5.commit();
            }
        });

        SharedPreferences sharedPreferencesRouteNum6 = this.getActivity().getSharedPreferences(SWITCH_ROUTE_NUM_6_TAG, MODE_PRIVATE);
        final SharedPreferences.Editor editorRouteNum6 = sharedPreferencesRouteNum6.edit();
        switchRouteNum6.setChecked(sharedPreferencesRouteNum6.getBoolean(SWITCH_ROUTE_NUM_6_TAG, false));
        switchRouteNum6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editorRouteNum6.putBoolean(SWITCH_ROUTE_NUM_6_TAG, true);
                } else {
                    editorRouteNum6.putBoolean(SWITCH_ROUTE_NUM_6_TAG, false);
                }
                editorRouteNum6.commit();
            }
        });


        return rootView;
    }
}
