package varmtech.com.weatherapp.general.viewmodel.adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import varmtech.com.weatherapp.R;
import varmtech.com.weatherapp.databinding.RecyclerViewRowItemBinding;
import varmtech.com.weatherapp.general.model.WeatherCountryModel;
import varmtech.com.weatherapp.general.viewmodel.listeners.EventHandler;

public class OwnRecyclerViewAdapter extends RecyclerView.Adapter<OwnRecyclerViewAdapter.ViewHolder> {
    private WeatherCountryModel weatherCountryModel;

    public OwnRecyclerViewAdapter(WeatherCountryModel weatherCountryModel) {
        this.weatherCountryModel = weatherCountryModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerViewRowItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.getContext()),
                R.layout.recycler_view_row_item, viewGroup, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.recyclerViewRowItemBinding.setCitymodel(weatherCountryModel.getCityWeather().get(position));
        holder.recyclerViewRowItemBinding.setEventHandler(new EventHandler());
    }

    @Override
    public int getItemCount() {
        return weatherCountryModel.getCnt();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerViewRowItemBinding recyclerViewRowItemBinding;

        public ViewHolder(RecyclerViewRowItemBinding recyclerViewRowItemBinding) {
            super(recyclerViewRowItemBinding.getRoot());
            this.recyclerViewRowItemBinding = recyclerViewRowItemBinding;
        }
    }
}
