package com.example.volodymyr.inventoryapp.ui.fragments.editproduct;

import android.content.Intent;

import com.example.volodymyr.inventoryapp.base.BasePresenter;
import com.example.volodymyr.inventoryapp.base.BaseView;
import com.example.volodymyr.inventoryapp.data.model.Product;

public interface EditProductContract {
    interface View extends BaseView<Presenter> {
        void initProduct(Product product);
        void initImageProduct(Product product);
        void setImageProduct(String imageLink);
    }

    interface Presenter extends BasePresenter<View> {
        void setProduct(long productId);
        void prepareProduct();
        void prepareImageProduct();
        void setImageResult(int requestCode, int requestId, int resultCode, Intent data);
        void editProduct(long productId,
                         String productImageLink,
                         String productName,
                         int price,
                         int quantity,
                         String supplierName,
                         String supplierPhoneNumber);

        boolean isFieldEmpty(String imageLink,
                             String productName,
                             int productQuantity,
                             String productSupplierName,
                             String productSupplierPhoneNumber);
    }
}
