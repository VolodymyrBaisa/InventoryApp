package com.example.volodymyr.inventoryapp.ui.main;

import android.util.Log;

import com.example.volodymyr.inventoryapp.data.DataManager;
import com.example.volodymyr.inventoryapp.data.model.Product;

import java.lang.ref.WeakReference;

import javax.inject.Inject;

public class MainPresenter implements MainContract.Presenter {
    private static final String LOG_TAG = MainPresenter.class.getSimpleName();

    @Inject
    protected DataManager mDataManager;
    private WeakReference<MainContract.View> mWeakReference;

    @Inject
    public MainPresenter() {
    }

    @Override
    public void takeView(MainContract.View view) {
        mWeakReference = new WeakReference<>(view);
    }

    @Override
    public void dropView() {
        mWeakReference.clear();
    }

    public void createProduct() {
        mDataManager.createProduct(new Product("3D Solutech Real White 3D Printer PLA Filament 1.75MM Filament 2.2 lb.",
                1799, 10, "3D Solutech", "(347)-99-9989"));

        mDataManager.createProduct(new Product("3D Solutech PETG175BLU See Through Blue 1.75 mm PETG 3D Printer Filament 2.2 lb.",
                1799, 5, "3D Solutech", "(347)-99-9989"));

        mDataManager.createProduct(new Product("3D Solutech Chocolate Brown 3D Printer PLA Filament 1.75MM Filament, Dimensional Accuracy +/- 0.03 mm, 2.2 LBS (1.0KG) - 100% USA",
                1799, 8, "3D Solutech", "(347)-99-9989"));

        mDataManager.createProduct(new Product("3D Solutech Beige 3D Printer PLA Filament 1.75MM Filament, Dimensional Accuracy +/- 0.03 mm, 2.2 LBS (1.0KG) - 100% USA",
                1899, 8, "3D Solutech", "(347)-99-9989"));

        Log.d(LOG_TAG, "Create products");
    }

    public void getProduct() {
        Product product = mDataManager.getProduct(1L);
        Log.d(LOG_TAG, "Product name: " + product.getProductName());
        Log.d(LOG_TAG, "Product price: " + product.getPrice());
        Log.d(LOG_TAG, "Product quantity: " + product.getQuantity());
        Log.d(LOG_TAG, "Supplier name: " + product.getSupplierName());
        Log.d(LOG_TAG, "Supplier phone number: " + product.getSupplierPhoneNumber());

    }
}