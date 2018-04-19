package com.example.volodymyr.inventoryapp.di.module;

import com.example.volodymyr.inventoryapp.ui.MainActivity;
import com.example.volodymyr.inventoryapp.ui.MainModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity mainActivity();
}
