package com.example.eadassignment;

import org.json.JSONObject;

import java.util.List;
import java.util.Queue;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface JSONPlaceholder {

    @GET("get-all")
    Call<List<Station>> getStation();

    @POST("User")
    Call<User> registerUser( @Body User user);

    @POST("FuelStation")
    Call<Station> CreateFuelStation( @Body Station station);

    @POST("FuelStation")
    Call<Station> AddFuelArrivalTime( @Body Station station);

    @POST("login")
    Call<User> logUser(@Body User user);

    @GET("search")
    Call<Station> searchStation(String id);

    @POST("QUEUE")
    Call<Queue> addArrivalTime(Queue queue);

    @GET("FuelStation/635ae4846934f11cbd6ea732")
    Call<Station> GetStation();
}
