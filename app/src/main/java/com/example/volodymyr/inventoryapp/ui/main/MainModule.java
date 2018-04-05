package com.example.volodymyr.inventoryapp.ui.main;

import com.example.volodymyr.inventoryapp.di.module.FragmentScoped;
import com.example.volodymyr.inventoryapp.ui.main.fragments.MainFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract MainFragment mainFragment();
}
