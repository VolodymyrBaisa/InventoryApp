package com.example.volodymyr.inventoryapp.ui.fragments.productdetails;

import com.example.volodymyr.inventoryapp.data.DataManager;
import com.example.volodymyr.inventoryapp.data.model.Product;

import java.lang.ref.WeakReference;

import javax.inject.Inject;

public class ProductDetailsPresenter implements ProductDetailsContract.Presenter {
    @Inject
    protected DataManager mDataManager;
    private Product mProduct;

    private WeakReference<ProductDetailsContract.View> mWeakReference;

    @Inject
    public ProductDetailsPresenter() {
    }

    @Override
    public void takeView(ProductDetailsContract.View view) {
        mWeakReference = new WeakReference<>(view);
    }

    @Override
    public void dropView() {
        if (mWeakReference.get() != null) mWeakReference.clear();
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
    public void deleteProduct(long productId) {
        mDataManager.deleteProduct(productId);
    }
}