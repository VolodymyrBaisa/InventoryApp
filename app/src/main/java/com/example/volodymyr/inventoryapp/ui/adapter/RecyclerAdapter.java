package com.example.volodymyr.inventoryapp.ui.adapter;

import android.app.Application;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.volodymyr.inventoryapp.R;
import com.example.volodymyr.inventoryapp.data.model.Product;
import com.example.volodymyr.inventoryapp.utils.ImageUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.inject.Inject;

public class RecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<Product> mProduct;
    private OnItemClickListener mListener;
    private Application mApplication;

    @Inject
    public RecyclerAdapter(Application application) {
        mApplication = application;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = mProduct.get(position);

        InputStream imageStream = ImageUtils.getImageStream(mApplication, product.getProductImageLink());
        holder.mProductPreviewImage.setImageBitmap(ImageUtils.getBitmapFromStream(imageStream));
        holder.mProductPreviewName.setText(product.getProductName());
        holder.mProductQuantity.setText(String.valueOf(product.getQuantity()));
        holder.mProductPrice.setText(String.valueOf(product.getPrice()));
        holder.bind(product.getId(), mListener);
    }

    @Override
    public int getItemCount() {
        if (mProduct != null && mProduct.size() > 0) {
            return mProduct.size();
        }
        return 0;
    }

    public void setProducts(List<Product> products) {
        mProduct = products;
        notifyDataSetChanged();
    }

    public void setOnClickItemListener(OnItemClickListener listener) {
        mListener = listener;
    }
}
