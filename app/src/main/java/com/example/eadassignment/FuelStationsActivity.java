package com.example.eadassignment;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FuelStationsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fuel_stations);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://fuel-station-app-backend.herokuapp.com/api/FuelStation/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JSONPlaceholder jsonPlaceholder = retrofit.create(JSONPlaceholder.class);
        Call<List<Station>> call = jsonPlaceholder.getStation();
        call.enqueue(new Callback<List<Station>>() {
            @Override
            public void onResponse(Call<List<Station>> call, Response<List<Station>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(FuelStationsActivity.this,response.code(),Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Station> stationList = response.body();
                StationAdapter stationAdapter = new StationAdapter(FuelStationsActivity.this,stationList);
                recyclerView.setAdapter(stationAdapter);
            }

            @Override
            public void onFailure(Call<List<Station>> call, Throwable t) {
                Toast.makeText(FuelStationsActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
