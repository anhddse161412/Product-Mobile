package com.mquan.productmarket.apdater;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mquan.productmarket.R;
import com.mquan.productmarket.model.Order;
import com.mquan.productmarket.model.OrderDetail;
import com.mquan.productmarket.viewholder.OrderHistoryViewHolder;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class OrderHistoryAdapter extends RecyclerView.Adapter<OrderHistoryViewHolder> {
    List<OrderDetail> listOrderDetail;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
    public OrderHistoryAdapter(List<OrderDetail> listOrderDetail) {
        this.listOrderDetail = listOrderDetail;
    }

    @NonNull
    @Override
    public OrderHistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_order_history, parent, false);
        return new OrderHistoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderHistoryViewHolder holder, int position) {
        OrderDetail orderDetail = listOrderDetail.get(position);
        String orderDate = simpleDateFormat.format(orderDetail.getOrder().getOrderDate());
        if(orderDetail == null){
            return;
        }
        Picasso.get().load(orderDetail.getProduct().getImage()).into(holder.img_product);
        holder.tv_order_id.setText(orderDetail.getOrder().getOrderId().toString());
        holder.tv_order_status.setText("Trạng thái: " + orderDetail.getOrder().getStatus());
        holder.tv_product_name.setText(orderDetail.getProduct().getName());
        holder.tv_order_date.setText("Ngày đặt: " + orderDate);
        holder.tv_order_price.setText("Giá: " + String.valueOf(orderDetail.getPrice()));
        holder.tv_order_quantity.setText("Số lượng: " + String.valueOf(orderDetail.getQuantity()));
    }

    @Override
    public int getItemCount() {
        return listOrderDetail.size();
    }
}
