package com.example.volodymyr.inventoryapp.ui.main.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.volodymyr.inventoryapp.R;
import com.example.volodymyr.inventoryapp.di.module.ActivityScoped;
import com.example.volodymyr.inventoryapp.ui.main.MainContract;
import com.example.volodymyr.inventoryapp.ui.main.MainPresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;

@ActivityScoped
public class EditProductFragment extends DaggerFragment implements MainContract.View {

    @Inject
    protected MainPresenter mMainPresenter;

    @Inject
    public EditProductFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_or_edit_product, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mMainPresenter.takeView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMainPresenter.dropView();
    }
}
