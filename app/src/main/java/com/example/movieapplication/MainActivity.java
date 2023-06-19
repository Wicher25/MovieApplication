package com.example.movieapplication;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnItemSelectedListener {

    BottomNavigationView bottomNavigationView;
    MyLibraryFragment myLibrary = new MyLibraryFragment ();
    DiscoverFragment  discover = new DiscoverFragment ();
    SettingsFragment  settings = new SettingsFragment ();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.discover_fr);

    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int itemId = item.getItemId();
        if (itemId == R.id.mylibrary_fr) {
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, myLibrary).commit();
            return true;
        } else if (itemId == R.id.discover_fr) {
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, discover).commit();
            return true;
        } else if (itemId == R.id.settings_fr) {
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, settings).commit();
            return true;
        }




        return false;
    }
}