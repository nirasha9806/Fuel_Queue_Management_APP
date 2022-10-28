package com.example.eadassignment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    TextView signUpText;
    Button loginBtn;
    EditText usernameEditText, passwordEdittext;
    String username,password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        signUpText = findViewById(R.id.signup);
        loginBtn = findViewById(R.id.loginBtn);
        usernameEditText = findViewById(R.id.username);
        passwordEdittext = findViewById(R.id.password);

        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(usernameEditText.getText().toString().equals("")) {
                    Toast.makeText(LoginActivity.this, "Please enter username", Toast.LENGTH_SHORT).show();
                    usernameEditText.setError("Username is required!");
                }
                else if(passwordEdittext.getText().toString().equals("")) {
                    Toast.makeText(LoginActivity.this, "Please enter password", Toast.LENGTH_SHORT).show();
                    passwordEdittext.setError("Password is required!");
                }
                else {
                    login();
                }
            }
        });
    }
    public void login(){
        username = usernameEditText.getText().toString();
        password = passwordEdittext.getText().toString();

        JSONPlaceholder jsonPlaceholder = RetrofitClient.getRetrofitInstance().create(JSONPlaceholder.class);
        Call<User> call = jsonPlaceholder.logUser(username);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.i("login",response.body().password);
                Log.i("login",password);
                Log.i("login",response.body().userType);


                if(response.body().password.equals(password)) {
                    Toast.makeText(LoginActivity.this, " User Logged", Toast.LENGTH_SHORT).show();
                    if(response.body().userType.equals("Station Owner")) {
                        Intent intent = new Intent(LoginActivity.this, UpdateFuelStatusActivity.class);
                        startActivity(intent);
                    }
                    if(response.body().userType.equals("Vehicle Owner")) {
                        Intent intent = new Intent(LoginActivity.this, FuelStationsActivity.class);
                        startActivity(intent);
                    }
                }
                else{
                    Toast.makeText(LoginActivity.this, " Invalid Password", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(LoginActivity.this, " Invalid Username", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
