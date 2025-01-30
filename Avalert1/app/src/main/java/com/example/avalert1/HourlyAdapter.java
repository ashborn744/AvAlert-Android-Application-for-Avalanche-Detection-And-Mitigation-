package com.example.avalert1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class HourlyAdapter extends RecyclerView.Adapter<HourlyAdapter.HourlyViewHolder> {

    private List<WeatherResponse.Hour> hourlyList;
    private Context context;

    public HourlyAdapter(Context context, List<WeatherResponse.Hour> hourlyList) {
        this.context = context;
        this.hourlyList = hourlyList;
    }

    @NonNull
    @Override
    public HourlyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hourly_card, parent, false);
        return new HourlyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HourlyViewHolder holder, int position) {
        WeatherResponse.Hour hour = hourlyList.get(position);

        String dateTime = hour.getTime();
        String[] dateTimeParts = dateTime.split(" ");

        // Extract the time part
        String time = dateTimeParts[1];

        // Set hour time
        holder.tvHour.setText(time);
        holder.tvTemperature.setText(String.valueOf(hour.getTemperatureC()) + "°C");

        // Load weather icon using Picasso or any other image loading library
        String iconUrl = "https:" + hour.getHourCondition().getIconUrl();
        Picasso.get().load(iconUrl).into(holder.ivWeatherIcon);
    }

    @Override
    public int getItemCount() {
        return hourlyList.size();
    }

    static class HourlyViewHolder extends RecyclerView.ViewHolder {
        TextView tvHour, tvTemperature;
        ImageView ivWeatherIcon;

        public HourlyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvHour = itemView.findViewById(R.id.tvHour);
            tvTemperature = itemView.findViewById(R.id.tvTemperature);
            ivWeatherIcon = itemView.findViewById(R.id.ivWeatherIcon);
        }
    }
}

