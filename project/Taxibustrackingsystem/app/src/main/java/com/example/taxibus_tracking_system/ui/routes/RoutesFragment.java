package com.example.taxibus_tracking_system.ui.routes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.taxibus_tracking_system.R;

public class RoutesFragment extends Fragment {

    private RoutesViewModel routesViewModel;
    private Switch switchBtnRouteNum4;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_routes, container, false);

        return root;
    }
}
