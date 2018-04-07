package com.example.volodymyr.inventoryapp.ui.main.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.volodymyr.inventoryapp.di.module.ActivityScoped;
import com.example.volodymyr.inventoryapp.ui.main.MainContract;
import com.example.volodymyr.inventoryapp.ui.main.MainPresenter;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

@ActivityScoped
public class MainFragment extends DaggerFragment implements MainContract.View {
    @Inject
    protected MainPresenter mMainPresenter;

    @Inject
    public MainFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        mMainPresenter.takeView(this);
        mMainPresenter.createProduct();
        mMainPresenter.getProduct();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMainPresenter.dropView();
    }
}
