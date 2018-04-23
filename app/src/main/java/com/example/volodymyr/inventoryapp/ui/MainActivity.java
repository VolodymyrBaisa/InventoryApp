package com.example.volodymyr.inventoryapp.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.volodymyr.inventoryapp.R;
import com.example.volodymyr.inventoryapp.data.DataManager;
import com.example.volodymyr.inventoryapp.ui.fragments.allinventory.AllInventoryFragment;
import com.example.volodymyr.inventoryapp.utils.ActivityUtils;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {
    @BindView(R.id.toolbar_view)
    protected Toolbar mToolbar;

    @Inject
    protected AllInventoryFragment mAllInventoryFragment;
    @Inject
    protected DataManager mDataManager;

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.delete_all_products) {
            deleteAllProducts();
        }
        return true;
    }

    private void deleteAllProducts() {
        mDataManager.deleteAllProducts();
        mAllInventoryFragment.setProducts(new ArrayList<>());
        if (!mAllInventoryFragment.isVisible()) {
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mAllInventoryFragment, R.id.fragment_container);
        }
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
