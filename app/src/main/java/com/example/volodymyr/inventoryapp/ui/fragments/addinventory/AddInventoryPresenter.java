package com.example.volodymyr.inventoryapp.ui.fragments.addinventory;

import android.graphics.Bitmap;

import com.example.volodymyr.inventoryapp.data.DataManager;
import com.example.volodymyr.inventoryapp.data.model.Product;
import com.example.volodymyr.inventoryapp.utils.FileUtils;
import com.example.volodymyr.inventoryapp.utils.ImageUtils;

import java.io.FileInputStream;
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
        mWeakReference.clear();
    }

    @Override
    public void createProduct(String productImage, String productName, int price, int quantity, String supplierName, String supplierPhoneNumber) {
        FileInputStream fileInputStream = FileUtils.readFile(productImage);
        Bitmap bitmap = ImageUtils.getBitmapFromInputStream(fileInputStream);
        byte[] bytes = ImageUtils.getBytesFromBitmap(bitmap);

        mDataManager.createProduct(new Product(-1, bytes, productName, price, quantity, supplierName, supplierPhoneNumber));
    }
}