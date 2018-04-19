package com.example.volodymyr.inventoryapp.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.volodymyr.inventoryapp.data.DataManager;
import com.example.volodymyr.inventoryapp.data.model.Product;
import com.example.volodymyr.inventoryapp.utils.ImageUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.ref.WeakReference;
import java.util.List;

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
        String filepath = "/storage/emulated/0/DCIM/100MEDIA/IMAG0002.jpg";
        File imagefile = new File(filepath);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(imagefile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Bitmap bm = BitmapFactory.decodeStream(fis);
        mDataManager.createProduct(new Product(ImageUtils.getBytesFromBitmap(bm), "3D Solutech Real White 3D Printer PLA Filament 1.75MM Filament 2.2 lb.",
                1799, 10, "3D Solutech", "(347)-99-9989"));

        Log.d(LOG_TAG, "Create products");
    }

    public void getProduct() {
        /*Product product = mDataManager.getProduct(1L);
        Log.d(LOG_TAG, "Product name: " + product.getProductName());
        Log.d(LOG_TAG, "Product price: " + product.getPrice());
        Log.d(LOG_TAG, "Product quantity: " + product.getQuantity());
        Log.d(LOG_TAG, "Supplier name: " + product.getSupplierName());
        Log.d(LOG_TAG, "Supplier phone number: " + product.getSupplierPhoneNumber());*/
        List<Product> products = mDataManager.getProducts();

        mWeakReference.get().setProducts(products);
    }


}