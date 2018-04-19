package com.example.volodymyr.inventoryapp.ui.adapter;

import com.example.volodymyr.inventoryapp.di.module.FragmentScoped;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Bios on 3/12/2018.
 */
@Module
public abstract class AdapterModule {
    @FragmentScoped
    @Provides
    static RecyclerAdapter getRecyclerAdapter() {
        return new RecyclerAdapter();
    }
}
