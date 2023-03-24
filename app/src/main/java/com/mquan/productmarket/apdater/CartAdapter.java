package com.mquan.productmarket.apdater;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mquan.productmarket.R;
import com.mquan.productmarket.model.Cart;
import com.mquan.productmarket.model.OrderDetail;
import com.mquan.productmarket.service.OnButtonClickListener;
import com.mquan.productmarket.viewholder.CartViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartViewHolder> {
    private Cart cart;
    private Context context;
    private List<OrderDetail> list;
    private OnButtonClickListener onButtonClickListener;
    public CartAdapter(Context context, Cart cart, OnButtonClickListener onButtonClickListener) {
        this.context = context;
        this.cart = cart;
        this.list =  new ArrayList<>(this.cart.getCart().values());
        this.onButtonClickListener = onButtonClickListener;
    }

    public void setData(Cart cart){
        this.cart = cart;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_cart_product, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        OrderDetail detail = list.get(position);
        if(detail == null){
            return;
        }
        Picasso.get().load(detail.getProduct().getImage()).into(holder.productImg);
        holder.productName.setText(detail.getProduct().getName());
        holder.productPrice.setText(String.valueOf(detail.getPrice() * detail.getQuantity()));
        holder.productQuantity.setText(String.valueOf(detail.getQuantity()));
        holder.bind(position, onButtonClickListener);

    }

    @Override
    public int getItemCount() {
        return cart.getCart().size();
    }

}
