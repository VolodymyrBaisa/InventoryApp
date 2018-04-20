package com.example.volodymyr.inventoryapp.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.volodymyr.inventoryapp.R;
import com.example.volodymyr.inventoryapp.data.model.Product;
import com.example.volodymyr.inventoryapp.di.module.ActivityScoped;
import com.example.volodymyr.inventoryapp.ui.fragments.allinventory.AllInventoryContract;
import com.example.volodymyr.inventoryapp.ui.fragments.allinventory.AllInventoryPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;

@ActivityScoped
public class ProductDetailsFragment extends DaggerFragment implements AllInventoryContract.View {

    @Inject
    protected AllInventoryPresenter mAllInventoryPresenter;

    @Inject
    public ProductDetailsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_details, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mAllInventoryPresenter.takeView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAllInventoryPresenter.dropView();
    }

    @Override
    public void setProducts(List<Product> products) {

    }
}
