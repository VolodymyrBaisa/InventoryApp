package com.example.volodymyr.inventoryapp.ui.fragments.productdetails;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.volodymyr.inventoryapp.R;
import com.example.volodymyr.inventoryapp.data.model.Product;
import com.example.volodymyr.inventoryapp.di.module.ActivityScoped;
import com.example.volodymyr.inventoryapp.ui.fragments.editproduct.EditProductFragment;
import com.example.volodymyr.inventoryapp.utils.ActivityUtils;
import com.example.volodymyr.inventoryapp.utils.ImageUtils;

import java.io.InputStream;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.DaggerFragment;

@ActivityScoped
public class ProductDetailsFragment extends DaggerFragment implements ProductDetailsContract.View {
    private final static String ID_KEY = "id_key_details";
    @Inject
    protected ProductDetailsPresenter mProductDetailsPresenter;
    @Inject
    protected EditProductFragment mEditProductFragment;

    @BindView(R.id.product_image)
    protected ImageView mProductImage;
    @BindView(R.id.product_name)
    protected TextView mProductEditName;
    @BindView(R.id.product_price)
    protected TextView mProductEditPrice;
    @BindView(R.id.product_quantity)
    protected TextView mProductEditQuantity;
    @BindView(R.id.product_supplier_name)
    protected TextView mProductEditSupplierName;
    @BindView(R.id.product_supplier_phone_number)
    protected TextView mProductEditSupplierPhoneNumber;

    @Inject
    public ProductDetailsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_details, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        setTitle(getContext(), R.string.details_product);

        mProductDetailsPresenter.takeView(this);
        if (getArguments() != null) {
            mProductDetailsPresenter.setProduct(getArguments().getLong(ID_KEY));
            mProductDetailsPresenter.prepareProduct();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mProductDetailsPresenter.dropView();
    }

    public void setProductId(long id) {
        Bundle bundle = new Bundle();
        bundle.putLong(ID_KEY, id);
        setArguments(bundle);
    }

    @Override
    public void initProduct(Product product) {
        InputStream imageStream = ImageUtils.getImageStream(getActivity(), product.getProductImageLink());
        mProductImage.setImageBitmap(ImageUtils.getBitmapFromStream(imageStream));
        mProductEditName.setText(product.getProductName());
        mProductEditPrice.setText(String.valueOf(product.getPrice()));
        mProductEditQuantity.setText(String.valueOf(product.getQuantity()));
        mProductEditSupplierName.setText(product.getSupplierName());
        mProductEditSupplierPhoneNumber.setText(String.valueOf(product.getSupplierPhoneNumber()));
    }

    @OnClick(R.id.delete_product)
    public void deleteProduct(View view) {
        mProductDetailsPresenter.deleteProduct(getArguments().getLong(ID_KEY));
        popBackFragment();
    }

    @OnClick(R.id.edit_product)
    public void editProduct(View view) {
        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager != null) {
            ActivityUtils.addFragmentToActivity(fragmentManager, mEditProductFragment, R.id.fragment_container);
            mEditProductFragment.setProductId(getArguments().getLong(ID_KEY));
        }
    }

    private void popBackFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager != null) {
            fragmentManager.popBackStack();
        }
    }
}
