package com.mquan.productmarket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mquan.productmarket.apdater.CategoryAdapter;
import com.mquan.productmarket.apdater.MainAdapter;
import com.mquan.productmarket.api.CategoryService;
import com.mquan.productmarket.api.ProductService;
import com.mquan.productmarket.model.Category;
import com.mquan.productmarket.model.Product;

import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements OnProductListener, OnCategoryListener {
    private String userId;
    private RecyclerView rcvProduct, rcvCategory;
    private MainAdapter productAdapter;
    private CategoryAdapter categoryAdapter;
    private TextView textHistory;
    private ImageView imgCart, menu;
    private SearchView searchView;
    private List<Product> listProducts;
    private List<Category> listCates;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String userId = intent.getStringExtra("USER_ID");
        if(userId == null){
            //this.userId = UUID.randomUUID().toString();
            this.userId = "84f6a9ab-fac8-44df-9549-8c428b91a66a";
        }
        SharedPreferences sharedPreferences = getSharedPreferences("USER", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("USER_ID", this.userId);
        editor.apply();

        textHistory = findViewById(R.id.text_history);
        imgCart = findViewById(R.id.shopping_cart);
        menu = findViewById(R.id.menu);
        searchView = findViewById(R.id.search_view);
        rcvProduct = findViewById(R.id.rcv_product);
        rcvCategory = findViewById(R.id.rcv_category);

        textHistory.setOnClickListener(v -> {
            Intent historyInput = new Intent(this, HistoryInputActivity.class);
            startActivity(historyInput);
        });

        imgCart.setOnClickListener(v -> {
            Intent cart = new Intent(this, CartActivity.class);
            startActivity(cart);
        });

        menu.setOnClickListener(v -> {
            Intent login = new Intent(this, LoginActivity.class);
            startActivity(login);
        });



        callApiProduct();
    }


    private void callApiProduct(){
        ProductService.productService.getListProduct(2023).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> list = response.body();
                listProducts = list;
                if(!list.isEmpty()){
                    productAdapter = new MainAdapter(list, MainActivity.this);
                    rcvProduct.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
                    rcvProduct.setAdapter(productAdapter);
                }
            }
            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "GET API FAILED", Toast.LENGTH_SHORT).show();
            }
        });
        //Call api Category
        CategoryService.categoryService.getListCategory().enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                List<Category> listCategory = response.body();
                listCates = listCategory;
                if(!listCategory.isEmpty()) {
                    categoryAdapter = new CategoryAdapter(listCategory, MainActivity.this);
                    rcvCategory.setLayoutManager(new GridLayoutManager(MainActivity.this, 4));
                    rcvCategory.setAdapter(categoryAdapter);
                }
            }
            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "GET API FAILED", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onProductClick(int position) {
        Intent detail = new Intent(MainActivity.this, ProductDetailActivity.class);
        detail.putExtra("PRODUCT_ID", listProducts.get(position).getProductId().toString());
        startActivity(detail);
    }

    @Override
    public void onCategoryClick(int position) {
        UUID categoryId = listCates.get(position).getCategoryId();
        ProductService.productService.getListProductWithCate(categoryId).enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> list = response.body();
                listProducts = list;
                if(!list.isEmpty()){
                    productAdapter.setData(listProducts);
                }
            }
            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "GET API FAILED", Toast.LENGTH_SHORT).show();
            }
        });
    }
}