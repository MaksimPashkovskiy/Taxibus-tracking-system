package com.example.taxibus_tracking_system;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesConfig {

    private SharedPreferences sharedPreferences;
    private Context context;

    public SharedPreferencesConfig(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(context.getResources().getString(R.string.shared_preferences), Context.MODE_PRIVATE);
    }

    public void writeRouteStatus(boolean status) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(context.getResources().getString(R.string.route_num_four_status_preference), status);
        editor.apply();
    }

    public boolean readRouteStatus() {
        boolean status;
        status = sharedPreferences.getBoolean(context.getResources().getString(R.string.route_num_four_status_preference), false);
        return status;
    }

}
