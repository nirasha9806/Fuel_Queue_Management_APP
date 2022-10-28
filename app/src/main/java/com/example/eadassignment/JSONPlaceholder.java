package com.example.eadassignment;

import org.json.JSONObject;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface JSONPlaceholder {

    @GET("get-all")
    Call<List<Station>> getStation();

    @POST("User")
    Call<User> registerUser( @Body User user);

    @POST("FuelStation")
    Call<Station> CreateFuelStation( @Body Station station);

    @POST("FuelStation")
    Call<Station> AddFuelArrivalTime( @Body Station station);

    @POST("User/login")
    Call<User> logUser(@Body String username);

    @GET("FuelStation/search/{name}")
    Call<List<Station>> searchStation(@Path(value = "name") String name);

    @GET("Queue")
    Call<List<Queue>> getQueueDetails();

    @GET("FuelStation/635ae4846934f11cbd6ea732")
    Call<Station> GetStation();
}
