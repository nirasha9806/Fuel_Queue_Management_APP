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

public class QueueWaitingListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.waiting_list);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        JSONPlaceholder jsonPlaceholder = RetrofitClient.getRetrofitInstance().create(JSONPlaceholder.class);
        Call<List<Queue>> call = jsonPlaceholder.getQueueDetails();
        call.enqueue(new Callback<List<Queue>>() {
            @Override
            public void onResponse(Call<List<Queue>> call, Response<List<Queue>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(QueueWaitingListActivity.this, response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Queue> queueList = response.body();
                QueueAdapter queueAdapter = new QueueAdapter(QueueWaitingListActivity.this, queueList);
                recyclerView.setAdapter(queueAdapter);
            }

            @Override
            public void onFailure(Call<List<Queue>> call, Throwable t) {
                Toast.makeText(QueueWaitingListActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
