package com.mquan.productmarket.apdater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mquan.productmarket.model.Customer;
import com.mquan.productmarket.R;
import com.mquan.productmarket.viewholder.CustomerViewHolder;

import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerViewHolder> {
    private Context context;
    private List<Customer> list;

    public CustomerAdapter(Context context, List<Customer> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomerViewHolder(LayoutInflater.from(context).inflate(R.layout.single_customer, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder holder, int position) {
        Customer customer = list.get(position);
        if(customer == null){
            return;
        }
        holder.customerName.setText("Tên: " + list.get(position).getFullName());
        holder.customerEmail.setText("Email: " + list.get(position).getEmail());
        holder.customerPhone.setText("Sđt: " + list.get(position).getPhone());
        holder.customerAddress.setText("Địa chỉ: " + list.get(position).getAddress());
        holder.customerCity.setText("Thành phố: " + list.get(position).getCity());
        holder.customerStatus.setText("Trạng thái: " + list.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
