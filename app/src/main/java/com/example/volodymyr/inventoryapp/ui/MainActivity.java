package com.example.volodymyr.inventoryapp.ui;

import android.os.Bundle;

import com.example.volodymyr.inventoryapp.R;
import com.example.volodymyr.inventoryapp.ui.fragments.AllInventoryFragment;
import com.example.volodymyr.inventoryapp.ui.fragments.ProductDetailsFragment;
import com.example.volodymyr.inventoryapp.utils.ActivityUtils;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {
    @Inject
    protected AllInventoryFragment mAllInventoryFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mAllInventoryFragment, R.id.fragment_container);
    }
}
