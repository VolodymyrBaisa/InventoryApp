package com.example.volodymyr.inventoryapp.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.volodymyr.inventoryapp.R;
import com.example.volodymyr.inventoryapp.data.model.Product;
import com.example.volodymyr.inventoryapp.di.module.ActivityScoped;
import com.example.volodymyr.inventoryapp.ui.MainContract;
import com.example.volodymyr.inventoryapp.ui.MainPresenter;
import com.example.volodymyr.inventoryapp.ui.adapter.RecyclerAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.DaggerFragment;

@ActivityScoped
public class AllInventoryFragment extends DaggerFragment implements MainContract.View {
    @Inject
    protected MainPresenter mMainPresenter;
    @Inject
    protected RecyclerAdapter mRecyclerAdapter;

    @BindView(R.id.product_list)
    protected RecyclerView mProductList;
    @BindView(R.id.add_product)
    protected FloatingActionButton mAddProduct;



    @Inject
    public AllInventoryFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_invertory, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        showProductList();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMainPresenter.takeView(this);
        mMainPresenter.createProduct();
        mMainPresenter.getProduct();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMainPresenter.dropView();
    }

    private void showProductList(){
        mProductList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mProductList.setLayoutManager(linearLayoutManager);
        mProductList.setAdapter(mRecyclerAdapter);
    }

    @Override
    public void setProducts(List<Product> products) {
        mRecyclerAdapter.setProducts(products);
    }
}
