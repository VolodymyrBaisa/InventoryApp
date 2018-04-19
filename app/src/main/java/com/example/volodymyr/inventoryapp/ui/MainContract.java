package com.example.volodymyr.inventoryapp.ui;

import com.example.volodymyr.inventoryapp.base.BasePresenter;
import com.example.volodymyr.inventoryapp.base.BaseView;
import com.example.volodymyr.inventoryapp.data.model.Product;

import java.util.List;

public interface MainContract {
    interface View extends BaseView<Presenter> {
        void setProducts(List<Product> products);
    }

    interface Presenter extends BasePresenter<View> {
        void getProduct();
    }
}
