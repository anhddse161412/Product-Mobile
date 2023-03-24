package com.mquan.productmarket.apdater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mquan.productmarket.R;
import com.mquan.productmarket.model.Cart;
import com.mquan.productmarket.model.OrderDetail;
import com.mquan.productmarket.viewholder.OrderCheckoutViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class OrderCheckoutAdapter extends RecyclerView.Adapter<OrderCheckoutViewHolder> {
    private List<OrderDetail> listProduct;
    private Cart cart;
    private Context context;

    public OrderCheckoutAdapter(Context context, Cart cart) {
        this.context = context;
        this.cart = cart;
        this.listProduct = new ArrayList<>(cart.getCart().values());
    }

    @NonNull
    @Override
    public OrderCheckoutViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_cart_checkout_product, parent, false);
        return new OrderCheckoutViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderCheckoutViewHolder holder, int position) {
        OrderDetail orderDetail = listProduct.get(position);
        if(orderDetail == null){
            return;
        }
        Picasso.get().load(orderDetail.getProduct().getImage()).into(holder.productImg);
        holder.productName.setText(orderDetail.getProduct().getName());
        holder.productPrice.setText( "Giá: " + (orderDetail.getPrice() * orderDetail.getQuantity()));
        holder.productQuantity.setText("Số lượng: " + orderDetail.getQuantity());
    }

    @Override
    public int getItemCount() {
        return listProduct.size();
    }
}
