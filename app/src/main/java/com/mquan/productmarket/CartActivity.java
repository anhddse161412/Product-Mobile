package com.mquan.productmarket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mquan.productmarket.apdater.CartAdapter;
import com.mquan.productmarket.api.CartService;
import com.mquan.productmarket.model.Cart;
import com.mquan.productmarket.model.OrderDetail;
import com.mquan.productmarket.service.OnButtonClickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity implements OnButtonClickListener {
    private CartAdapter cartAdapter;
    private TextView textHistory;
    private ImageView imgCart;
    private Button btnBack, btnCheckout;
    private RecyclerView recyclerView;
    private Cart cart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cart_layout);

        textHistory = findViewById(R.id.text_history);
        imgCart = findViewById(R.id.shopping_cart);
        btnBack = findViewById(R.id.btn_back);
        btnCheckout = findViewById(R.id.btn_order);
        recyclerView = findViewById(R.id.rcv_cart);
        SharedPreferences sharedPreferences = getSharedPreferences("USER", MODE_PRIVATE);
        String userId = sharedPreferences.getString("USER_ID", null);
        Log.d("USER_ID", userId);
        btnBack.setOnClickListener(v -> {
            finish();
        });

        btnCheckout.setOnClickListener(v -> {
            Intent orderForm = new Intent(this, CustomerOrderFormActivity.class);
            startActivity(orderForm);
        });

        textHistory.setOnClickListener(v -> {
            Intent historyInput = new Intent(this, HistoryInputActivity.class);
            startActivity(historyInput);
        });

        imgCart.setOnClickListener(v -> {

        });
        callApiGetCart(userId);
    }

    private void callApiUpdateCartById(String cartId, Cart cart) {
        CartService.cartService.updateCart(cartId, cart).enqueue(new Callback<Cart>() {
            @Override
            public void onResponse(Call<Cart> call, Response<Cart> response) {
                if (response.isSuccessful()) {
                    Log.d("TEST", "UPDATE");
                }
            }
            @Override
            public void onFailure(Call<Cart> call, Throwable t) {
                Log.d("TEST", "CANT UPDATE");
            }
        });
    }

    private void callApiGetCart(String userId) {
        CartService.cartService.getCartWithId(userId).enqueue(new Callback<Cart>() {
            @Override
            public void onResponse(Call<Cart> call, Response<Cart> response) {
                Cart getCart = response.body();
                cart = getCart;
                if (getCart != null) {
                    recyclerView.setLayoutManager(new GridLayoutManager(CartActivity.this, 1));
                    cartAdapter = new CartAdapter(CartActivity.this, getCart, CartActivity.this);
                    recyclerView.setAdapter(cartAdapter);
                } else {
                    Log.d("TEST", "GET CART NULL");
                }
            }
            @Override
            public void onFailure(Call<Cart> call, Throwable t) {
                Toast.makeText(CartActivity.this, "GET API FAILED", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onButtonClick(int position, String buttonName) {
        SharedPreferences sharedPreferences = getSharedPreferences("USER", MODE_PRIVATE);
        String userId = sharedPreferences.getString("USER_ID", null);
        List<OrderDetail> listOrderDetail = new ArrayList<>(cart.getCart().values());
        UUID productId = listOrderDetail.get(position).getProduct().getProductId();
        if (buttonName.equalsIgnoreCase("INCREASE")) {
            cart.editItem(productId, cart.getCart().get(productId).getQuantity() + 1);
            callApiUpdateCartById(userId, cart);
            cartAdapter.setData(cart);
        }
        if (buttonName.equalsIgnoreCase("DECREASE")) {
            cart.editItem(productId, cart.getCart().get(productId).getQuantity() - 1);
            callApiUpdateCartById(userId, cart);
            cartAdapter.setData(cart);
        }
        if (buttonName.equalsIgnoreCase("REMOVE")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Do you want to delete " + listOrderDetail.get(position).getProduct().getName() + "?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    cart.removeItem(productId);
                    callApiUpdateCartById(userId, cart);
                    cartAdapter.setData(cart);
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
}