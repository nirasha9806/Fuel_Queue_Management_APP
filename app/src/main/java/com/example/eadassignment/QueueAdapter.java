package com.example.eadassignment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class QueueAdapter extends RecyclerView.Adapter<QueueAdapter.QueueViewHolder> {
    List<Queue> queueList;
    Context context;

    public QueueAdapter(Context context, List<Queue> queues){
        this.context = context;
        queueList = queues;
    }

    @NonNull
    @Override
    public QueueViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(context).inflate(R.layout.queuitem, parent, false);
        return new QueueViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QueueViewHolder holder, int position) {
        Queue queue = queueList.get(position);
        holder.name.setText(queue.getUsername());
        holder.vehicleType.setText(queue.getVehicleType());
        holder.arrivalTime.setText(queue.getArrivalTime() + " to");
        holder.departTime.setText(queue.getDepartTime());
    }

    @Override
    public int getItemCount() {
        return queueList.size();
    }

    public class QueueViewHolder extends RecyclerView.ViewHolder{
        TextView name, vehicleType,arrivalTime, departTime;


        public QueueViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            vehicleType = itemView.findViewById(R.id.vehicleType);
            arrivalTime = itemView.findViewById(R.id.arrivalTime);
            departTime = itemView.findViewById(R.id.departTime);
        }
    }
}
