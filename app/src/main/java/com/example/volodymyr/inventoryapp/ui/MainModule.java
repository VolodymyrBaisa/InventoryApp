package com.example.volodymyr.inventoryapp.ui;

import com.example.volodymyr.inventoryapp.di.module.ActivityScoped;
import com.example.volodymyr.inventoryapp.di.module.FragmentScoped;
import com.example.volodymyr.inventoryapp.ui.adapter.AdapterModule;
import com.example.volodymyr.inventoryapp.ui.fragments.addinventory.AddInventoryContract;
import com.example.volodymyr.inventoryapp.ui.fragments.allinventory.AllInventoryContract;
import com.example.volodymyr.inventoryapp.ui.fragments.addinventory.AddInventoryFragment;
import com.example.volodymyr.inventoryapp.ui.fragments.allinventory.AllInventoryFragment;
import com.example.volodymyr.inventoryapp.ui.fragments.editproduct.EditProductFragment;
import com.example.volodymyr.inventoryapp.ui.fragments.productdetails.ProductDetailsContract;
import com.example.volodymyr.inventoryapp.ui.fragments.productdetails.ProductDetailsFragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainModule {
    @FragmentScoped
    @ContributesAndroidInjector(modules = AdapterModule.class)
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
    abstract AllInventoryContract.Presenter allInventoryPresenter(AllInventoryContract.Presenter presenter);

    @ActivityScoped
    @Binds
    abstract AddInventoryContract.Presenter addInventoryPresenter(AddInventoryContract.Presenter presenter);

    @ActivityScoped
    @Binds
    abstract ProductDetailsContract.Presenter addIDetailsPresenter(ProductDetailsContract.Presenter presenter);
}
