package com.example.volodymyr.inventoryapp.ui.fragments.addinventory;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.volodymyr.inventoryapp.R;
import com.example.volodymyr.inventoryapp.di.module.ActivityScoped;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.DaggerFragment;

@ActivityScoped
public class AddInventoryFragment extends DaggerFragment implements AddInventoryContract.View {
    @Inject
    protected AddInventoryPresenter mAddInventoryPresenter;

    @Inject
    public AddInventoryFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_or_edit_product, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mAddInventoryPresenter.takeView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAddInventoryPresenter.dropView();
    }

    @OnClick(R.id.save_product)
    public void saveNewProduct(View view) {
        //mAddInventoryPresenter.createProduct();
    }
}