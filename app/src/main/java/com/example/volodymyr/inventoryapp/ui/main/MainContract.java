package com.example.volodymyr.inventoryapp.ui.main;

import com.example.volodymyr.inventoryapp.base.BasePresenter;
import com.example.volodymyr.inventoryapp.base.BaseView;

public interface MainContract {
    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter<View> {}
}
