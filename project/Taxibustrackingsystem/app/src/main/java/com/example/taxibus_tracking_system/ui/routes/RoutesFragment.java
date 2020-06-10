package com.example.taxibus_tracking_system.ui.routes;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.taxibus_tracking_system.R;
import com.example.taxibus_tracking_system.SharedPreferencesConfig;

public class RoutesFragment extends Fragment {

    public static final String TAG = "RoutesFragment";

    private Switch switchRouteNum4;

    private static final String SWITCH_ROUTE_NUM_4_TAG = "switchRouteNum4";
    private static final String ON_INPUT_SELECTED = "mOnInputSelected";
    /*private DBHelper mDBHelper;
    private SQLiteDatabase mDb;
     */
    private SharedPreferencesConfig preferencesConfig;

    public interface OnStatusSendListener {
        void onStatusSend(Boolean status);
    }

    public OnStatusSendListener statusSendListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        try {
            statusSendListener = (OnStatusSendListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement onMessageSend...");
        }
    }

    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_routes, container, false);
        preferencesConfig = new SharedPreferencesConfig(requireActivity());
        switchRouteNum4 = view.findViewById(R.id.switchRouteNum4);
        switchRouteNum4.setChecked(preferencesConfig.readRouteStatus());

        /*SharedPreferences sharedPreferencesRouteNum4 = this.requireActivity().getSharedPreferences(SWITCH_ROUTE_NUM_4_TAG, MODE_PRIVATE);
        final SharedPreferences.Editor editorRouteNum4 = sharedPreferencesRouteNum4.edit();
        switchRouteNum4.setChecked(sharedPreferencesRouteNum4.getBoolean(SWITCH_ROUTE_NUM_4_TAG, false));*/

        switchRouteNum4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
//                    editorRouteNum4.putBoolean(SWITCH_ROUTE_NUM_4_TAG, true);
                    preferencesConfig.writeRouteStatus(true);
//                    statusSendListener.onStatusSend(true);
                    Toast.makeText(getContext(), "True", Toast.LENGTH_SHORT).show();
                } else {
//                    editorRouteNum4.putBoolean(SWITCH_ROUTE_NUM_4_TAG, false);
//                    statusSendListener.onStatusSend(false);
                    preferencesConfig.writeRouteStatus(false);
                    Toast.makeText(getContext(), "False", Toast.LENGTH_SHORT).show();
                }
//                editorRouteNum4.apply();
            }
        });

        /*SharedPreferences sharedPreferencesRouteNum4 = this.requireActivity().getSharedPreferences(SWITCH_ROUTE_NUM_4_TAG, MODE_PRIVATE);
        final SharedPreferences.Editor editorRouteNum4 = sharedPreferencesRouteNum4.edit();
        switchRouteNum4.setChecked(sharedPreferencesRouteNum4.getBoolean(SWITCH_ROUTE_NUM_4_TAG, false));

        switchRouteNum4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    editorRouteNum4.putBoolean(SWITCH_ROUTE_NUM_4_TAG, true);
                    String message = String.valueOf(true);
                    messageSendListener.onMessageSend(message);
                } else {
                    editorRouteNum4.putBoolean(SWITCH_ROUTE_NUM_4_TAG, false);
                    String message = String.valueOf(false);
                    messageSendListener.onMessageSend(message);
                }
                editorRouteNum4.apply();
            }
        });*/

        /*mDBHelper = new DBHelper(this.getActivity());
        try {
            mDBHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }
        try {
            mDb = mDBHelper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }*/

        /*try {
            SharedPreferences sharedPreferencesRouteNum4 = this.requireActivity().getSharedPreferences(SWITCH_ROUTE_NUM_4_TAG, MODE_PRIVATE);
            final SharedPreferences.Editor editorRouteNum4 = sharedPreferencesRouteNum4.edit();
            switchRouteNum4.setChecked(sharedPreferencesRouteNum4.getBoolean(SWITCH_ROUTE_NUM_4_TAG, false));
            switchRouteNum4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        editorRouteNum4.putBoolean(SWITCH_ROUTE_NUM_4_TAG, true);
                        mOnInputSelected.sendInput(true);

                        String product = "";
                        Cursor cursor = mDb.rawQuery("SELECT * FROM route", null);
                        cursor.moveToFirst();
                        while (!cursor.isAfterLast()) {
                            product += cursor.getString(1) + " | ";
                            cursor.moveToNext();
                        }
                        cursor.close();
                        Toast.makeText(getContext(), product, Toast.LENGTH_SHORT).show();

                    } else {
                        editorRouteNum4.putBoolean(SWITCH_ROUTE_NUM_4_TAG, false);
                        mOnInputSelected.sendInput(false);

                    }
                    editorRouteNum4.apply();
                }
            });

            SharedPreferences sharedPreferencesRouteNum5 = this.requireActivity().getSharedPreferences(SWITCH_ROUTE_NUM_5_TAG, MODE_PRIVATE);
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
                    editorRouteNum5.apply();
                }
            });

            SharedPreferences sharedPreferencesRouteNum6 = this.requireActivity().getSharedPreferences(SWITCH_ROUTE_NUM_6_TAG, MODE_PRIVATE);
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
                    editorRouteNum6.apply();
                }
            });
        } catch (Exception ex) {
            Toast.makeText(this.getContext(), "Something wrong", Toast.LENGTH_SHORT).show();
        }*/

        return view;
    }
}
