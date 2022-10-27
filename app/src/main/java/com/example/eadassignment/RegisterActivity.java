package com.example.eadassignment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    Spinner spinner;
    TextView textView;
    Button button;
    EditText cityEditText, usernameEditText, passwordEditText, vehicleTypeEditText,StationNameEditText ;
    String username, password,userType, vehicleType, stationName, city;
    List<JSONObject> fuelDetails ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_screen);
        spinner = findViewById(R.id.spinner);
        textView = findViewById(R.id.signIn);
        button = findViewById(R.id.registerBtn);
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        vehicleTypeEditText = findViewById(R.id.vehicleType);
        StationNameEditText  = findViewById(R.id.vehicleType);
        cityEditText = findViewById(R.id.city);

        ///set the drop down values with visibility
        String[] value = {"Vehicle Owner","Station Owner"};
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(value));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,R.layout.spinner_list,arrayList);
        Spinner.OnItemSelectedListener listener = new Spinner.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(spinner.getSelectedItem().toString().equals("Station Owner")){
                    cityEditText.setVisibility(View.VISIBLE);
                    vehicleTypeEditText.setHint("Station Name");
                }
                else {
                    cityEditText.setVisibility(View.INVISIBLE);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        };
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(listener);

        ///intent to the sign in page
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonClicked();
            }
        });
    }

    //create user API call
    private  void buttonClicked(){
        userType = spinner.getSelectedItem().toString();
        username = usernameEditText.getText().toString();
        password = passwordEditText.getText().toString();
        if(userType.equals("Vehicle Owner")) {
            vehicleType = vehicleTypeEditText.getText().toString();
        }

        User user = new User(username,password,vehicleType, userType);

        JSONPlaceholder jsonPlaceholder = RetrofitClient.getRetrofitInstance().create(JSONPlaceholder.class);
        Call<User> call = jsonPlaceholder.registerUser(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Toast.makeText(RegisterActivity.this, "You registered successfully", Toast.LENGTH_SHORT).show();

                //if user type is Station Owner create fuel station
                if(userType.equals("Station Owner")) {
                    addStation();
                }
                ///intent to the sign in page using register button
                Intent intent = new Intent(RegisterActivity.this,UpdateFuelStatusActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    //fuel station API call
    private void addStation(){
        stationName = StationNameEditText.getText().toString();
        city = cityEditText.getText().toString();

        Station station = new Station(stationName,city,fuelDetails);

        JSONPlaceholder jsonPlaceholder = RetrofitClient.getRetrofitInstance().create(JSONPlaceholder.class);
        Call<Station> call = jsonPlaceholder.CreateFuelStation(station);
        call.enqueue(new Callback<Station>() {
            @Override
            public void onResponse(Call<Station> call, Response<Station> response) {
                Log.i("Success", "success");
            }

            @Override
            public void onFailure(Call<Station> call, Throwable t) {
                Log.i("Error", "error");
            }
        });
    }
}
