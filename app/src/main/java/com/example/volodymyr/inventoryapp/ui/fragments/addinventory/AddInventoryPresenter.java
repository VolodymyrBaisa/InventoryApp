package com.example.volodymyr.inventoryapp.ui.fragments.addinventory;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;

import com.example.volodymyr.inventoryapp.data.DataManager;
import com.example.volodymyr.inventoryapp.data.model.Product;
import com.example.volodymyr.inventoryapp.utils.ImageUtils;

import java.io.InputStream;
import java.lang.ref.WeakReference;

import javax.inject.Inject;

public class AddInventoryPresenter implements AddInventoryContract.Presenter {
    @Inject
    protected DataManager mDataManager;

    private WeakReference<AddInventoryContract.View> mWeakReference;

    @Inject
    public AddInventoryPresenter() {
    }

    @Override
    public void takeView(AddInventoryContract.View view) {
        mWeakReference = new WeakReference<>(view);
    }

    @Override
    public void dropView() {
       if(mWeakReference.get() != null) mWeakReference.clear();
    }

    @Override
    public void createProduct(String productImageLink, String productName, int price, int quantity, String supplierName, String supplierPhoneNumber) {
        mDataManager.createProduct(new Product(productImageLink, productName, price, quantity, supplierName, supplierPhoneNumber));
    }

    @Override
    public void setImageResult(int requestCode, int requestId, int resultCode, Intent data) {
        if (requestCode == requestId && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                mWeakReference.get().setImageProduct(String.valueOf(data.getData()));
            }
        }
    }
}