package com.example.movieapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

public class SettingsFragment extends Fragment {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private TextView fridgeTextView;
    private SwitchCompat duzyTextSwitch;
    private SwitchCompat ciemnyMotywSwitch;

    public SettingsFragment() {
        // Required empty public constructor
    }

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize SharedPreferences
        sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        fridgeTextView = view.findViewById(R.id.SettingsTextView);
        duzyTextSwitch = view.findViewById(R.id.duzy_text);

        // Retrieve Dark Mode and Large Text state
        boolean isDarkMode = sharedPreferences.getBoolean("darkModeState", false);
        boolean isLargeText = sharedPreferences.getBoolean("largeTextState", false);

        // Update layout colors and text size
        updateLayoutColors(isDarkMode);
        updateTextSize(isLargeText);

        duzyTextSwitch.setChecked(isLargeText);
        duzyTextSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor.putBoolean("largeTextState", isChecked);
                editor.apply();

                // Update text size
                updateTextSize(isChecked);
            }
        });

        SwitchCompat ciemnyMotywSwitch = view.findViewById(R.id.ciemny_motyw);
        ciemnyMotywSwitch.setChecked(isDarkMode);
        ciemnyMotywSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor.putBoolean("darkModeState", isChecked);
                editor.apply();

                // Update layout colors
                updateLayoutColors(isChecked);
            }
        });

        return view;
    }

    private void updateLayoutColors(boolean isDarkMode) {
        int backgroundColor = isDarkMode ? R.color.dark_background : R.color.light_background;
        int textColor = isDarkMode ? R.color.dark_text : R.color.light_text;

        View view = getView();
        if (view != null) {
            view.setBackgroundColor(getResources().getColor(backgroundColor));
            fridgeTextView.setTextColor(getResources().getColor(textColor));

            SwitchCompat ciemny_motywSwitch = view.findViewById(R.id.ciemny_motyw);
            ciemny_motywSwitch.setTextColor(getResources().getColor(textColor));

            SwitchCompat duzy_textSwitch = view.findViewById(R.id.duzy_text);
            duzy_textSwitch.setTextColor(getResources().getColor(textColor));
        }
    }

    private void updateTextSize(boolean isLargeText) {
        float textSize = isLargeText ? 60f : 40f;
        fridgeTextView.setTextSize(textSize);

    }
}
