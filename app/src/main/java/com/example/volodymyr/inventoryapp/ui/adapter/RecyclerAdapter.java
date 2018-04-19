package com.example.volodymyr.inventoryapp.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.volodymyr.inventoryapp.R;
import com.example.volodymyr.inventoryapp.data.model.Product;
import com.example.volodymyr.inventoryapp.utils.ImageUtils;

import java.util.List;

import javax.inject.Inject;

public class RecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<Product> mProduct;

    @Inject
    public RecyclerAdapter() {
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

        holder.mProductPreviewImage.setImageBitmap(ImageUtils.decodeByteArray(product.getProductImage()));
        holder.mProductPreviewName.setText(product.getProductName());
        holder.mProductQuantity.setText(String.valueOf(product.getQuantity()));
        holder.mProductPrice.setText(String.valueOf(product.getPrice()));
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
}
