package com.example.volodymyr.inventoryapp.ui.fragments.editproduct;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.volodymyr.inventoryapp.R;
import com.example.volodymyr.inventoryapp.data.model.Product;
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
public class EditProductFragment extends DaggerFragment implements EditProductContract.View {
    public final static int PICK_IMAGE_FOR_PRODUCT = 101;
    private final static String ID_KEY = "id_key_edit";

    @Inject
    protected EditProductPresenter mEditProductPresenter;

    @BindView(R.id.product_image)
    protected ImageView mProductImage;
    @BindView(R.id.product_name)
    protected EditText mProductEditName;
    @BindView(R.id.product_price)
    protected EditText mProductEditPrice;
    @BindView(R.id.product_quantity)
    protected TextView mProductQuantity;
    @BindView(R.id.product_supplier_name)
    protected EditText mProductEditSupplierName;
    @BindView(R.id.product_supplier_phone_number)
    protected EditText mProductEditSupplierPhoneNumber;

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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTitle(getContext(), R.string.edit_product);
        mEditProductPresenter.takeView(this);
        if (getArguments() != null) {
            mEditProductPresenter.setProduct(getArguments().getLong(ID_KEY));
            mEditProductPresenter.prepareImageProduct();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mEditProductPresenter.prepareProduct();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mEditProductPresenter.dropView();
    }

    public void setProductId(long id) {
        Bundle bundle = new Bundle();
        bundle.putLong(ID_KEY, id);
        setArguments(bundle);
    }

    @Override
    public void initProduct(Product product) {
        mProductEditName.setText(product.getProductName());
        mProductEditPrice.setText(String.valueOf(product.getPrice()));
        mProductQuantity.setText(String.valueOf(product.getQuantity()));
        mProductEditSupplierName.setText(product.getSupplierName());
        mProductEditSupplierPhoneNumber.setText(String.valueOf(product.getSupplierPhoneNumber()));
    }

    public void initImageProduct(Product product) {
        InputStream imageStream = ImageUtils.getImageStream(getActivity(), product.getProductImageLink());
        mProductImage.setImageBitmap(ImageUtils.getBitmapFromStream(imageStream));
        mProductImage.setContentDescription(product.getProductImageLink());
    }

    @OnClick(R.id.product_image)
    public void setImageProduct(View view) {
        ActivityUtils.openImageGallery(this, PICK_IMAGE_FOR_PRODUCT);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mEditProductPresenter.setImageResult(requestCode, PICK_IMAGE_FOR_PRODUCT, resultCode, data);
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
    public void editProduct(View view) {
        String imageLink = String.valueOf(mProductImage.getContentDescription());
        String productName = String.valueOf(mProductEditName.getText()).trim();
        int productPrice = IntegerUtils.parseInt(String.valueOf(mProductEditPrice.getText()).trim());
        int productQuantity = IntegerUtils.parseInt(String.valueOf(mProductQuantity.getText()).trim());
        String productSupplierName = String.valueOf(mProductEditSupplierName.getText()).trim();
        String productSupplierPhoneNumber = String.valueOf(mProductEditSupplierPhoneNumber.getText()).trim();

        if (mEditProductPresenter.isFieldEmpty(imageLink,
                productName,
                productQuantity,
                productSupplierName,
                productSupplierPhoneNumber)) {

            mEditProductPresenter.editProduct(
                    getArguments().getLong(ID_KEY),
                    imageLink,
                    productName,
                    productPrice,
                    productQuantity,
                    productSupplierName,
                    productSupplierPhoneNumber);

            popBackFragment();
        } else {
            Toast.makeText(getContext(), R.string.fill_correct_lines_error, Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.product_add_quantity)
    public void addQuantity(View view) {
        int productQuantity = IntegerUtils.parseInt(String.valueOf(mProductQuantity.getText()).trim());
        mProductQuantity.setText(String.valueOf(++productQuantity));
    }

    @OnClick(R.id.product_remove_quantity)
    public void remove_quantity(View view) {
        int productQuantity = IntegerUtils.parseInt(String.valueOf(mProductQuantity.getText()).trim());
        if (productQuantity != 0) mProductQuantity.setText(String.valueOf(--productQuantity));
    }

    private void popBackFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager != null) {
            fragmentManager.popBackStack();
        }
    }
}
