package com.example.volodymyr.inventoryapp.data;

import com.example.volodymyr.inventoryapp.data.model.Product;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class DataManager {

    private DbHelper mDbHelper;

    @Inject
    public DataManager(DbHelper dbHelper) {
        this.mDbHelper = dbHelper;
    }

    public Long createProduct(Product product) {
        return mDbHelper.insertProduct(product);
    }

    public Product getProduct(Long productId) {
        return mDbHelper.getProduct(productId);
    }

    public int editProduct(Long productId, Product product){
        return mDbHelper.updateProduct(productId, product);
    }

    public void deleteAllProducts(){
        mDbHelper.deleteAllProducts();
    }

    public void deleteProduct(Long productId){
        mDbHelper.deleteProduct(productId);
    }
}
