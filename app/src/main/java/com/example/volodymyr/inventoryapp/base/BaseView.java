package com.example.volodymyr.inventoryapp.base;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

public interface BaseView<T> {
    default void setTitle(Context context, @StringRes int title) {
        ActionBar actionBar = ((AppCompatActivity) context).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(title);
        }
    }
}
