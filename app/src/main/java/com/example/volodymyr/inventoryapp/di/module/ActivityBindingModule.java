package com.example.volodymyr.inventoryapp.di.module;

import com.example.volodymyr.inventoryapp.ui.main.MainActivity;
import com.example.volodymyr.inventoryapp.ui.main.MainModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = MainModule.class)
    abstract MainActivity mainActivity();
}
