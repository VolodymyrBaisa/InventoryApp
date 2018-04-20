package com.example.volodymyr.inventoryapp.ui.fragments.addinventory;

import com.example.volodymyr.inventoryapp.base.BasePresenter;
import com.example.volodymyr.inventoryapp.base.BaseView;
import com.example.volodymyr.inventoryapp.data.model.Product;

import java.util.List;

public interface AddInventoryContract {
    interface View extends BaseView<Presenter> {
    }

    interface Presenter extends BasePresenter<View> {
        void createProduct(String productImage,
                           String productName,
                           int price,
                           int quantity,
                           String supplierName,
                           String supplierPhoneNumber);
    }
}
