package com.mquan.productmarket.apdater;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mquan.productmarket.OnProductListener;
import com.mquan.productmarket.R;
import com.mquan.productmarket.Searchable;
import com.mquan.productmarket.model.Category;
import com.mquan.productmarket.model.Product;
import com.mquan.productmarket.viewholder.MainViewHolder;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class MainAdapter extends RecyclerView.Adapter<MainViewHolder> implements Searchable {
    private List<Product> mListProduct, mListProductCopy;
    private List<Product> filteredListProduct;
    private OnProductListener onProductListener;

    public MainAdapter(List<Product> mListProduct, OnProductListener onProductListener) {
        this.mListProduct = mListProduct;
        this.mListProductCopy = mListProduct;
        this.onProductListener = onProductListener;
    }

    public void setData(List<Product> list){
        this.mListProduct = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_product, parent, false);
        return new MainViewHolder(view, onProductListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        Product product = mListProduct.get(position);
        if (product == null) {
            return;
        }
        Picasso.get().load(product.getImage()).into(holder.productImg);
        holder.productTitle.setText(product.getName());
        holder.productBrand.setText("Hãng: " + product.getBrand().getBrandName());
        holder.productQuantity.setText("Số lượng: " + product.getQuantity());

    }

    @Override
    public int getItemCount() {
        if (mListProduct != null) {
            return mListProduct.size();
        }
        return 0;
    }

    @Override
    public void search(String value) {
        if(value.isEmpty() || value == null){
            mListProduct = mListProductCopy;
            notifyDataSetChanged();
        } else {
            filteredListProduct = new ArrayList<>();
            for (Product product : mListProduct) {
                if (product.getName().toLowerCase().contains(value.toLowerCase())) {
                    filteredListProduct.add(product);
                }
            }
            mListProduct = filteredListProduct;
            notifyDataSetChanged();
        }
    }


}
