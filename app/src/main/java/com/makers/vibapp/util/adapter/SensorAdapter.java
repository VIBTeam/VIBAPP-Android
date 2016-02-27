package com.makers.vibapp.util.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.makers.vibapp.R;
import com.makers.vibapp.data.model.VibSensor;

import java.util.ArrayList;

/**
 * Created by Eliseo on 20/02/2016.
 */
public class SensorAdapter extends RecyclerView.Adapter<SensorAdapter.ViewHolder> {

    ArrayList<VibSensor> sensors;
    private OnItemClickListener listener;

    public SensorAdapter(ArrayList<VibSensor> sensors) {
        this.sensors = sensors;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_sensor, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final VibSensor sensor = sensors.get(position);
        holder.titleTextView.setText(sensor.getTitle());
        holder.macTextView.setText(sensor.getBluetoothDevice().getAddress());
        holder.enableSwitch.setEnabled(sensor.getEnabled());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    listener.onItemClickListener(sensor);
                } catch (NullPointerException ignore){}
            }
        });
    }

    @Override
    public int getItemCount() {
        return sensors.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView;
        TextView macTextView;
        Switch enableSwitch;

        public ViewHolder(View itemView) {
            super(itemView);
            titleTextView = (TextView) itemView.findViewById(R.id.textViewSensorRowTitle);
            macTextView = (TextView) itemView.findViewById(R.id.textViewSensorRowMac);
            enableSwitch = (Switch) itemView.findViewById(R.id.switchSensorRowEnable);
        }
    }

    public interface OnItemClickListener{
        void onItemClickListener(VibSensor sensor);
    }
}
