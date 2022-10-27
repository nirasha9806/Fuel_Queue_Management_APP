package com.example.eadassignment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateFuelStatusActivity extends AppCompatActivity {

    EditText stationName, arrivalTime, fuelType, addressEdit;
    Button button;
    String name, time, type, address;
//    FuelDetails[] fuelDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_fuel_arrival_depart_time);

        stationName = findViewById(R.id.fuelStationName);
        arrivalTime = findViewById(R.id.updateFuelArrival);
        fuelType = findViewById(R.id.updateFuelType);
        button = findViewById(R.id.addBtn);
        addressEdit = findViewById(R.id.stationAddress);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addFuelArrivalTime();
            }
        });
    }

    public void addFuelArrivalTime(){
        name = stationName.getText().toString();
        time = arrivalTime.getText().toString();
        type = fuelType.getText().toString();
        address = addressEdit.getText().toString();
        List<JSONObject> fuelDetails = new ArrayList<JSONObject>();

        JSONObject fuelObject = new JSONObject();
        try {
            fuelObject.put("arrivalTime", time);
            fuelObject.put("finishTime", "");
            fuelObject.put("fuelType", type);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        FuelDetails object = new FuelDetails("11.00","12.00","petrol");

        fuelDetails.add(fuelObject);

        Station station = new Station(name,address,fuelDetails);
        Log.i("object",fuelObject.toString());

//        Log.i("object",fuelDetails.toString());

        JSONPlaceholder jsonPlaceholder = RetrofitClient.getRetrofitInstance().create(JSONPlaceholder.class);
        Call<Station> call = jsonPlaceholder.AddFuelArrivalTime(station);
        call.enqueue(new Callback<Station>() {
            @Override
            public void onResponse(Call<Station> call, Response<Station> response) {
                Toast.makeText(UpdateFuelStatusActivity.this, type +  " arrival time added", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UpdateFuelStatusActivity.this,UpdateFinishTime.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<Station> call, Throwable t) {
                Log.i("Error", "error");
            }
        });


    }
}
