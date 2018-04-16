package com.example.volodymyr.inventoryapp.ui.main;

import com.example.volodymyr.inventoryapp.di.module.ActivityScoped;
import com.example.volodymyr.inventoryapp.di.module.FragmentScoped;
import com.example.volodymyr.inventoryapp.ui.main.fragments.AddInventoryFragment;
import com.example.volodymyr.inventoryapp.ui.main.fragments.AllInventoryFragment;
import com.example.volodymyr.inventoryapp.ui.main.fragments.EditProductFragment;
import com.example.volodymyr.inventoryapp.ui.main.fragments.ProductDetailsFragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract AllInventoryFragment mAllInventoryFragment();

    @FragmentScoped
    @ContributesAndroidInjector
    abstract AddInventoryFragment mAddInventoryFragment();

    @FragmentScoped
    @ContributesAndroidInjector
    abstract EditProductFragment mEditProductFragment();

    @FragmentScoped
    @ContributesAndroidInjector
    abstract ProductDetailsFragment mProductDetailsFragment();

    @ActivityScoped
    @Binds
    abstract MainContract.Presenter mainPresenter(MainContract.Presenter presenter);
}
