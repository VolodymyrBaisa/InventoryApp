package com.example.volodymyr.inventoryapp.ui.fragments.addinventory;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.volodymyr.inventoryapp.R;
import com.example.volodymyr.inventoryapp.di.module.ActivityScoped;
import com.example.volodymyr.inventoryapp.utils.ActivityUtils;
import com.example.volodymyr.inventoryapp.utils.ImageUtils;
import com.example.volodymyr.inventoryapp.utils.IntegerUtils;

import java.io.InputStream;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dagger.android.support.DaggerFragment;

@ActivityScoped
public class AddInventoryFragment extends DaggerFragment implements AddInventoryContract.View {
    public final static int PICK_IMAGE_FOR_PRODUCT = 100;
    @Inject
    protected AddInventoryPresenter mAddInventoryPresenter;

    @BindView(R.id.product_image)
    protected ImageView mProductImage;
    @BindView(R.id.product_name)
    protected EditText mProductEditName;
    @BindView(R.id.product_price)
    protected EditText mProductEditPrice;
    @BindView(R.id.product_quantity)
    protected EditText mProductEditQuantity;
    @BindView(R.id.product_supplier_name)
    protected EditText mProductEditSupplierName;
    @BindView(R.id.product_supplier_phone_number)
    protected EditText mProductEditSupplierPhoneNumber;

    @Inject
    public AddInventoryFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_or_edit_product, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTitle(getContext(), R.string.add_product);
    }

    @Override
    public void onResume() {
        super.onResume();
        mAddInventoryPresenter.takeView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAddInventoryPresenter.dropView();
    }

    @OnClick(R.id.product_image)
    public void setImageProduct(View view) {
        ActivityUtils.openImageGallery(this, PICK_IMAGE_FOR_PRODUCT);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mAddInventoryPresenter.setImageResult(requestCode, PICK_IMAGE_FOR_PRODUCT, resultCode, data);
    }

    @Override
    public void setImageProduct(String imageLink) {
        InputStream imageStream = ImageUtils.getImageStream(getContext(), imageLink);
        Bitmap bitmap = ImageUtils.getBitmapFromStream(imageStream);
        if (bitmap != null) {
            mProductImage.setImageBitmap(bitmap);
            mProductImage.setContentDescription(imageLink);
        }
    }

    @OnClick(R.id.save_product)
    public void saveNewProduct(View view) {
        String imageLink = String.valueOf(mProductImage.getContentDescription());
        String productName = String.valueOf(mProductEditName.getText()).trim();
        int productPrice = IntegerUtils.parseInt(String.valueOf(mProductEditPrice.getText()).trim());
        int productQuantity = IntegerUtils.parseInt(String.valueOf(mProductEditQuantity.getText()).trim());
        String productSupplierName = String.valueOf(mProductEditSupplierName.getText()).trim();
        String productSupplierPhoneNumber = String.valueOf(mProductEditSupplierPhoneNumber.getText()).trim();

        mAddInventoryPresenter.createProduct(
                imageLink,
                productName,
                productPrice,
                productQuantity,
                productSupplierName,
                productSupplierPhoneNumber);

        popBackFragment();
        clearProductDescription();
    }

    private void popBackFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager != null) {
            fragmentManager.popBackStack();
        }
    }

    private void clearProductDescription() {
        mProductEditName.setText("");
        mProductEditPrice.setText("");
        mProductEditQuantity.setText("");
        mProductEditSupplierName.setText("");
        mProductEditSupplierPhoneNumber.setText("");
    }
}
