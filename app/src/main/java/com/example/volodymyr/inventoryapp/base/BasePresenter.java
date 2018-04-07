package com.example.volodymyr.inventoryapp.base;

public interface BasePresenter<T> {
    void takeView(T view);

    void dropView();
}
