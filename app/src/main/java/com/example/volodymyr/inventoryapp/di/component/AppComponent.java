package com.example.volodymyr.inventoryapp.di.component;

import android.app.Application;

import com.example.volodymyr.inventoryapp.InventoryApp;
import com.example.volodymyr.inventoryapp.di.module.ActivityBindingModule;
import com.example.volodymyr.inventoryapp.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class,
        ActivityBindingModule.class,
        ApplicationModule.class})
public interface AppComponent extends AndroidInjector<InventoryApp> {
    @Component.Builder
    interface Builder{
        @BindsInstance
        AppComponent.Builder application(Application application);

        AppComponent build();
    }
}
