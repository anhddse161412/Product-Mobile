package com.mquan.productmarket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mquan.productmarket.apdater.OrderHistoryAdapter;
import com.mquan.productmarket.api.CustomerService;
import com.mquan.productmarket.api.OrderService;
import com.mquan.productmarket.model.OrderDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderHistoryActivity extends AppCompatActivity {
    private TextView textHistory;
    private ImageView imgCart;
    private Button btnBack;
    private RecyclerView rcvOrderHistory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_history);

        textHistory = findViewById(R.id.text_history);
        imgCart = findViewById(R.id.shopping_cart);
        btnBack = findViewById(R.id.btn_back);
        rcvOrderHistory = findViewById(R.id.rcv_order_history);

        btnBack.setOnClickListener(v -> {
            finish();
        });

        imgCart.setOnClickListener(v -> {
            Intent cart = new Intent(this, CartActivity.class);
            startActivity(cart);
        });
        callApiOrderHistory();
    }

    private void callApiOrderHistory() {
        Intent intent = getIntent();
        String email = intent.getStringExtra("EMAIL");
        OrderService.orderService.getOrderHistory("ee8ee11d-4db7-46f8-bd44-a50c5932a2b6").enqueue(new Callback<List<OrderDetail>>() {
            @Override
            public void onResponse(Call<List<OrderDetail>> call, Response<List<OrderDetail>> response) {
                List<OrderDetail> listOrderHistory = response.body();
                if(listOrderHistory != null){
                    if(!listOrderHistory.isEmpty()){
                        Toast.makeText(OrderHistoryActivity.this, "GET API SUCCESS", Toast.LENGTH_SHORT).show();
                        OrderHistoryAdapter orderHistoryAdapter = new OrderHistoryAdapter(listOrderHistory);
                        rcvOrderHistory.setLayoutManager(new GridLayoutManager(OrderHistoryActivity.this, 1));
                        rcvOrderHistory.setAdapter(orderHistoryAdapter);
                    }
                }
            }
            @Override
            public void onFailure(Call<List<OrderDetail>> call, Throwable t) {
                Toast.makeText(OrderHistoryActivity.this, "GET API FAILED", Toast.LENGTH_SHORT).show();
            }
        });
    }
}