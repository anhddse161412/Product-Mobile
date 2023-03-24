package com.mquan.productmarket;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mquan.productmarket.api.LoginService;
import com.mquan.productmarket.model.Account;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText loginUserName, loginPassword;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        btnLogin = findViewById(R.id.btnLogin);
        loginUserName = findViewById(R.id.inputLoginUsername);
        loginPassword = findViewById(R.id.inputLoginPassword);

        //Go to Home page after login
        btnLogin.setOnClickListener(view -> {
            String userName = loginUserName.getText().toString();
            String password = loginPassword.getText().toString();
            callApiLogin(userName, password);
        });
    }

    private void callApiLogin(String email, String password){
        LoginService.loginService.getLoginAccount(email).enqueue(new Callback<Account[]>() {
            @Override
            public void onResponse(Call<Account[]> call, Response<Account[]> response) {
                Account[] account = response.body();
                if(account.length == 1) {
                    if (account[0].getEmail().equals(email) && account[0].getPassword().equals(password)) {

                        Intent i = new Intent(LoginActivity.this, CustomerViewActivity.class);
                        startActivity(i);
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "WRONG EMAIL OR PASSWORD", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Account[]> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "GET API FAILED", Toast.LENGTH_LONG).show();
            }
        });
    }
}