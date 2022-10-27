package com.example.eadassignment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateFinishTime extends AppCompatActivity {
    TextView typeTextView, arrivalTextView;
    EditText departEditText;
    Button btn;
    String departTime;
    TextView typeTextView2, arrivalTextView2;
    EditText departEditText2;
    Button btn2;
    String departTime2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_fuel_finish_time);
        typeTextView = findViewById(R.id.type);
        arrivalTextView = findViewById(R.id.arrivalTime);
        departEditText = findViewById(R.id.departTime);
        btn = findViewById(R.id.addBtn);
        typeTextView2 = findViewById(R.id.type2);
        arrivalTextView2 = findViewById(R.id.arrivalTime2);
        departEditText2 = findViewById(R.id.departTime2);
        btn2 = findViewById(R.id.addBtn2);

        JSONPlaceholder jsonPlaceholder = RetrofitClient.getRetrofitInstance().create(JSONPlaceholder.class);
        Call<Station> call = jsonPlaceholder.GetStation();
        call.enqueue(new Callback<Station>() {
            @Override
            public void onResponse(Call<Station> call, Response<Station> response) {
                typeTextView.setText("Petrol");
                arrivalTextView.setText("12.00 p.m");
                typeTextView2.setText("Diesel");
                arrivalTextView2.setText("1.00 p.m");
            }

            @Override
            public void onFailure(Call<Station> call, Throwable t) {
                Log.i("Error", "error");
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDepartTime();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addDepartTime();
            }
        });
    }

    public void addDepartTime(){
        departTime = departEditText.getText().toString();
        List<JSONObject> fuelDetails = new ArrayList<JSONObject>();

        JSONObject fuelObject = new JSONObject();
        try {
            fuelObject.put("arrivalTime", "");
            fuelObject.put("finishTime", departTime);
            fuelObject.put("fuelType", "");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        fuelDetails.add(fuelObject);

        Station station = new Station("name","address",fuelDetails);


        JSONPlaceholder jsonPlaceholder = RetrofitClient.getRetrofitInstance().create(JSONPlaceholder.class);
        Call<Station> call = jsonPlaceholder.AddFuelArrivalTime(station);
        call.enqueue(new Callback<Station>() {
            @Override
            public void onResponse(Call<Station> call, Response<Station> response) {
                Toast.makeText(UpdateFinishTime.this,  " finish time added", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UpdateFinishTime.this,UpdateFuelStatusActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Station> call, Throwable t) {
                Log.i("Error", "error");
            }
        });
    }

}
