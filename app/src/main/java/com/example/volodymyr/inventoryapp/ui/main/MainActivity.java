package com.example.volodymyr.inventoryapp.ui.main;

import android.os.Bundle;

import com.example.volodymyr.inventoryapp.R;
import com.example.volodymyr.inventoryapp.ui.main.fragments.MainFragment;
import com.example.volodymyr.inventoryapp.utils.ActivityUtils;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {
    @Inject
    MainFragment mMainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mMainFragment, R.id.fragment_container);
    }
}
