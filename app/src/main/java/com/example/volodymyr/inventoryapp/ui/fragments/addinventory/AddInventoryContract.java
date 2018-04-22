package com.example.volodymyr.inventoryapp.ui.fragments.addinventory;

import android.content.Intent;

import com.example.volodymyr.inventoryapp.base.BasePresenter;
import com.example.volodymyr.inventoryapp.base.BaseView;

public interface AddInventoryContract {
    interface View extends BaseView<Presenter> {
        void setImageProduct(String imageLink);
    }

    interface Presenter extends BasePresenter<View> {
        void createProduct(String productImageLink,
                           String productName,
                           int price,
                           int quantity,
                           String supplierName,
                           String supplierPhoneNumber);

        void setImageResult(int requestCode, int requestId, int resultCode, Intent data);
    }
}
