package com.example.volodymyr.inventoryapp.ui.fragments.allinventory;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.volodymyr.inventoryapp.R;
import com.example.volodymyr.inventoryapp.data.model.Product;
import com.example.volodymyr.inventoryapp.di.module.ActivityScoped;
import com.example.volodymyr.inventoryapp.ui.adapter.OnItemClickListener;
import com.example.volodymyr.inventoryapp.ui.adapter.RecyclerAdapter;
import com.example.volodymyr.inventoryapp.ui.fragments.ProductDetailsFragment;
import com.example.volodymyr.inventoryapp.ui.fragments.addinventory.AddInventoryFragment;
import com.example.volodymyr.inventoryapp.utils.ActivityUtils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.DaggerFragment;

@ActivityScoped
public class AllInventoryFragment extends DaggerFragment implements AllInventoryContract.View {
    @Inject
    protected AllInventoryPresenter mAllInventoryPresenter;
    @Inject
    protected RecyclerAdapter mRecyclerAdapter;
    @Inject
    protected AddInventoryFragment mAddInventoryFragment;
    @Inject
    protected ProductDetailsFragment mProductDetailsFragment;

    @BindView(R.id.product_list)
    protected RecyclerView mProductList;

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
        initProductList();
    }

    @Override
    public void onResume() {
        super.onResume();
        mAllInventoryPresenter.takeView(this);
        mAllInventoryPresenter.showProductsList();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAllInventoryPresenter.dropView();
    }

    private void initProductList() {
        mProductList.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mProductList.setLayoutManager(linearLayoutManager);
        mProductList.setAdapter(mRecyclerAdapter);
        mRecyclerAdapter.setOnClickItemListener(new OnItemClickListener() {
            @Override
            public void onItemClick(long itemId) {
                FragmentManager fragmentManager = getFragmentManager();
                if (fragmentManager != null) {
                    ActivityUtils.addFragmentToActivity(fragmentManager, mProductDetailsFragment, R.id.fragment_container);
                }
            }
        });
    }

    @Override
    public void setProducts(List<Product> products) {
        mRecyclerAdapter.setProducts(products);
    }

    @OnClick({R.id.add_product})
    public void addNewProduct(View view) {
        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager != null) {
            ActivityUtils.addFragmentToActivity(fragmentManager, mAddInventoryFragment, R.id.fragment_container);
        }
    }
}
