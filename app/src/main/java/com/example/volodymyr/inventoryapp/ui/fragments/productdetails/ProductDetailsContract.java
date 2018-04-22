package com.example.volodymyr.inventoryapp.ui.fragments.productdetails;

import com.example.volodymyr.inventoryapp.base.BasePresenter;
import com.example.volodymyr.inventoryapp.base.BaseView;
import com.example.volodymyr.inventoryapp.data.model.Product;

public interface ProductDetailsContract {
    interface View extends BaseView<Presenter> {
        void initProduct(Product product);
    }

    interface Presenter extends BasePresenter<View> {
        void setProduct(long productId);
        void prepareProduct();
        void deleteProduct(long productId);
    }
}
