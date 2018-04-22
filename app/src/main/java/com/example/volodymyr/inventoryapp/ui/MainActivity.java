package com.example.volodymyr.inventoryapp.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.example.volodymyr.inventoryapp.R;
import com.example.volodymyr.inventoryapp.ui.fragments.allinventory.AllInventoryFragment;
import com.example.volodymyr.inventoryapp.utils.ActivityUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {
    @BindView(R.id.toolbar_view)
    protected Toolbar mToolbar;

    @Inject
    protected AllInventoryFragment mAllInventoryFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowHomeEnabled(true);
        }
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mAllInventoryFragment, R.id.fragment_container);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() <= 1) {
            finish();
        } else {
            super.onBackPressed();
        }
    }
}
