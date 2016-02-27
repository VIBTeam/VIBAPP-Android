package com.makers.vibapp.util.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.makers.vibapp.R;
import com.makers.vibapp.data.model.VibSoundType;

import java.util.ArrayList;

/**
 * Created by Eliseo on 26/02/2016.
 */
public class SoundAdapter extends RecyclerView.Adapter<SoundAdapter.ViewHolder> {

    ArrayList<VibSoundType> soundTypes;
    OnSoundClickListener listener;

    public SoundAdapter(ArrayList<VibSoundType> soundTypes) {
        this.soundTypes = soundTypes;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_sound, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final VibSoundType soundType = soundTypes.get(position);
        holder.imageView.setImageResource(soundType.getImage());
        holder.textView.setText(soundType.getTitle());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onSoundClickListener(soundType);
            }
        });
    }

    public void setOnSoundClickListener(OnSoundClickListener listener){
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return soundTypes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageViewRowSound);
            textView = (TextView) itemView.findViewById(R.id.textViewRowTitle);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    view.setSelected(!view.isSelected());
                }
            });
        }
    }

    public interface OnSoundClickListener{
        void onSoundClickListener(VibSoundType vibSoundType);
    }
}
