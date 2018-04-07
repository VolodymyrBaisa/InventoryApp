package com.example.volodymyr.inventoryapp.ui.main;

import com.example.volodymyr.inventoryapp.di.module.ActivityScoped;
import com.example.volodymyr.inventoryapp.di.module.FragmentScoped;
import com.example.volodymyr.inventoryapp.ui.main.fragments.MainFragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract MainFragment mainFragment();

    @ActivityScoped
    @Binds
    abstract MainContract.Presenter mainPresenter(MainContract.Presenter presenter);
}
