package com.example.volodymyr.inventoryapp.ui.fragments.allinventory;

import android.view.View;

import com.example.volodymyr.inventoryapp.data.DataManager;
import com.example.volodymyr.inventoryapp.data.model.Product;

import java.lang.ref.WeakReference;
import java.util.List;

import javax.inject.Inject;

public class AllInventoryPresenter implements AllInventoryContract.Presenter {
    @Inject
    protected DataManager mDataManager;
    private WeakReference<AllInventoryContract.View> mWeakReference;

    @Inject
    public AllInventoryPresenter() {
    }

    @Override
    public void takeView(AllInventoryContract.View view) {
        mWeakReference = new WeakReference<>(view);
    }

    @Override
    public void dropView() {
        if(mWeakReference.get() != null) mWeakReference.clear();
    }

    @Override
    public void showProductsList() {
        List<Product> products = mDataManager.getProducts();
        if(products != null && products.size() > 0){
            mWeakReference.get().setMessageIfListEmpty(View.GONE);
        } else {
            mWeakReference.get().setMessageIfListEmpty(View.VISIBLE);
        }
        mWeakReference.get().setProducts(products);
    }
}