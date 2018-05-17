package com.example.volodymyr.inventoryapp.ui.fragments.editproduct;

import android.app.Activity;
import android.content.Intent;

import com.example.volodymyr.inventoryapp.data.DataManager;
import com.example.volodymyr.inventoryapp.data.model.Product;

import java.lang.ref.WeakReference;

import javax.inject.Inject;

public class EditProductPresenter implements EditProductContract.Presenter {
    @Inject
    protected DataManager mDataManager;
    private Product mProduct;

    private WeakReference<EditProductContract.View> mWeakReference;

    @Inject
    public EditProductPresenter() {
    }

    @Override
    public void takeView(EditProductContract.View view) {
        mWeakReference = new WeakReference<>(view);
    }

    @Override
    public void dropView() {
        if (mWeakReference != null && mWeakReference.get() != null) mWeakReference.clear();
    }

    @Override
    public void setProduct(long productId) {
        if (mDataManager.isProductExists(productId)) {
            mProduct = mDataManager.getProduct(productId);
        }
    }

    @Override
    public void prepareProduct() {
        mWeakReference.get().initProduct(mProduct);
    }

    @Override
    public void setImageResult(int requestCode, int requestId, int resultCode, Intent data) {
        if (requestCode == requestId && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                mWeakReference.get().setImageProduct(String.valueOf(data.getData()));
            }
        }
    }

    @Override
    public void editProduct(long productId,
                            String productImageLink,
                            String productName,
                            int price,
                            int quantity,
                            String supplierName,
                            String supplierPhoneNumber) {

        mDataManager.editProduct(productId,
                new Product(productImageLink, productName, price, quantity, supplierName, supplierPhoneNumber));
    }
}