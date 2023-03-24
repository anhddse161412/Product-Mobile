package com.mquan.productmarket.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mquan.productmarket.R;

public class OrderCheckoutViewHolder extends RecyclerView.ViewHolder {
    public TextView productName, productPrice, productQuantity;
    public ImageView productImg;
    public OrderCheckoutViewHolder(@NonNull View itemView) {
        super(itemView);
        productImg = itemView.findViewById(R.id.product_image);
        productName = itemView.findViewById(R.id.product_name);
        productPrice = itemView.findViewById(R.id.product_price);
        productQuantity = itemView.findViewById(R.id.product_quantity);
    }
}
