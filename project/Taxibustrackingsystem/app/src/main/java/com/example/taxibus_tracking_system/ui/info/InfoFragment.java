package com.example.taxibus_tracking_system.ui.info;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.taxibus_tracking_system.R;
import com.example.taxibus_tracking_system.SharedPreferencesConfig;

public class InfoFragment extends Fragment {

    public static final String TAG = "InfoFragment";

    /*public static final String SWITCH_ROUTE_NUM_42_TAG = "SWITCH_ROUTE_NUM_42_TAG";

    private SharedPreferencesConfig preferencesConfig;
    private TextView textView;
    private Switch aSwitch2;
    private Switch aSwitch3;*/

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_info, container, false);
        SharedPreferencesConfig preferencesConfig = new SharedPreferencesConfig(requireActivity());
        Switch aSwitch1 = view.findViewById(R.id.switchRouteNum42);
        aSwitch1.setChecked(preferencesConfig.readRouteStatus());

        /*textView = view.findViewById(R.id.someText);
        aSwitch1 = view.findViewById(R.id.switchRouteNum42);
        aSwitch2 = view.findViewById(R.id.switchRouteNum52);
        aSwitch3 = view.findViewById(R.id.switchRouteNum62);

        savedInstanceState = getArguments();

        if (savedInstanceState != null) {
            boolean status = savedInstanceState.getBoolean("status");
            textView.setText(Boolean.toString(status));
            aSwitch1.setChecked(status);

            SharedPreferences sharedPreferencesRouteNum42 = this.requireActivity().getSharedPreferences(SWITCH_ROUTE_NUM_42_TAG, MODE_PRIVATE);
            final SharedPreferences.Editor editorRouteNum42 = sharedPreferencesRouteNum42.edit();
            aSwitch.setChecked(sharedPreferencesRouteNum42.getBoolean(SWITCH_ROUTE_NUM_42_TAG, Boolean.parseBoolean(message)));
            editorRouteNum42.putBoolean(SWITCH_ROUTE_NUM_42_TAG, true);
            editorRouteNum42.apply();
        }*/

        return view;
    }
}
