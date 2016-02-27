package com.makers.vibapp.fragment;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.makers.vibapp.R;
import com.makers.vibapp.data.model.VibSensor;
import com.makers.vibapp.util.adapter.SensorAdapter;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.ArrayList;

public class SensorFragment extends Fragment implements SensorAdapter.OnItemClickListener {

    private OnSensorClickListener mListener;
    private FloatingActionButton fabAdd;

    public SensorFragment() {
        // Required empty public constructor
    }

    public static SensorFragment newInstance() {
        SensorFragment fragment = new SensorFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sensor, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewSensorFragment);
        fabAdd = (FloatingActionButton) view.findViewById(R.id.fabSensorFragmentAdd);

        ArrayList<VibSensor> sensors = new ArrayList<>();

        SensorAdapter adapter = new SensorAdapter(sensors);
        adapter.setOnItemClickListener(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onAddItemButton();
            }
        });

        return view;
    }

    @Override
    public void onItemClickListener(VibSensor sensor) {
        try {
            mListener.onSensorClick(sensor);
        } catch (NullPointerException ignore) {
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSensorClickListener) {
            mListener = (OnSensorClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnSensorClickListener {
        void onSensorClick(VibSensor sensor);

        void onAddItemButton();
    }
}
