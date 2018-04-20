package com.example.volodymyr.inventoryapp.ui.fragments.allinventory;

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
        mWeakReference.clear();
    }

    @Override
    public void showProductsList() {
        List<Product> products = mDataManager.getProducts();
        if(products != null){
            mWeakReference.get().setProducts(products);
        }
    }
}