package com.example.volodymyr.inventoryapp.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.volodymyr.inventoryapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewHolder extends RecyclerView.ViewHolder {
  @BindView(R.id.product_preview_image)
  protected ImageView mProductPreviewImage;

  @BindView(R.id.product_preview_name)
  protected TextView mProductPreviewName;

  @BindView(R.id.product_preview_quantity)
  protected TextView mProductQuantity;

  @BindView(R.id.product_preview_price)
  protected TextView mProductPrice;

    public ViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
