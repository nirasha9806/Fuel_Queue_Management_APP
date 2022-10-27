package com.example.eadassignment;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StationAdapter extends RecyclerView.Adapter<StationAdapter.StationViewHolder> {

    List<Station> stationList;
    Context context;

    public StationAdapter(Context context, List<Station> stations){
        this.context = context;
        stationList = stations;
    }
    @NonNull
    @Override
    public StationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new StationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StationViewHolder holder, int position) {
        Station station = stationList.get(position);
        holder.name.setText(station.getName());
        holder.city.setText(station.getCity());
    }

    @Override
    public int getItemCount() {
        return stationList.size();
    }

    public class StationViewHolder extends RecyclerView.ViewHolder{
        TextView name, city;

        public StationViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            city = itemView.findViewById(R.id.city);
        }
    }
}
