package com.makers.vibapp.fragment;

import android.app.Fragment;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.makers.vibapp.R;
import com.makers.vibapp.activity.MainActivity;
import com.makers.vibapp.data.DataManager;
import com.makers.vibapp.data.model.VibNotification;
import com.makers.vibapp.data.model.VibSensor;
import com.makers.vibapp.util.VibService;
import com.makers.vibapp.util.adapter.BluetoothDeviceAdapter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;


public class SensorEditorFragment extends Fragment {
    private static final String ARG_SENSOR_ID = "sensorId";

    private FloatingActionButton addSensorButton;
    private Button scanButton;
    private BluetoothDeviceAdapter availableDevicesAdapter;
    private EditText sensorMacEditText;
    private Button sensorCalibrateButton;
    private EditText sensorNameEditText;
    private ListView listView;
    private InteractionListener listener;
    private BluetoothDevice currentBluetoothDevice;

    public SensorEditorFragment() {

    }

    public static SensorEditorFragment newInstance(VibSensor sensor) {
        SensorEditorFragment fragment = new SensorEditorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SENSOR_ID, sensor.getBluetoothDevice().getAddress());
        fragment.setArguments(args);
        return fragment;
    }

    public static SensorEditorFragment newInstance() {
        SensorEditorFragment fragment = new SensorEditorFragment();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            String sensorId = getArguments().getString(ARG_SENSOR_ID);
            VibSensor sensor = DataManager.getInstance().getVibSensorsById(sensorId);
        } catch (NullPointerException ignore) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sensor_editor, container, false);
        sensorNameEditText = (EditText) view.findViewById(R.id.editTextSensorEditorName);
        sensorMacEditText = (EditText) view.findViewById(R.id.editTextSensorEditorMac);
        addSensorButton = (FloatingActionButton) view.findViewById(R.id.fabSensorFragmentSave);
        listView = (ListView) view.findViewById(R.id.listViewSensorEditor);
        addSensorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    VibSensor sensor = new VibSensor(sensorNameEditText.getText().toString(), true, currentBluetoothDevice);
                    listener.onSaveSensor(sensor);
                } catch (NullPointerException e) {
                }
            }
        });
        listView.setAdapter(availableDevicesAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                currentBluetoothDevice = availableDevicesAdapter.getItem(i);
            }
        });
        availableDevicesAdapter = new BluetoothDeviceAdapter(getActivity(), android.R.layout.simple_list_item_1);
        return view;
    }

    private void startDiscovery(){
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        adapter.startDiscovery();
        BroadcastReceiver mReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();

                //Finding devices
                if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                    // Get the BluetoothDevice object from the Intent
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    // Add the name and address to an array adapter to show in a ListView
                    availableDevicesAdapter.add(device);
                }
            }
        };

        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        getActivity().registerReceiver(mReceiver, filter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof InteractionListener) {
            listener = (InteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        ((MainActivity) getActivity()).vibService.stopScan();
        listener = null;

    }

    public interface InteractionListener {
        void onSaveSensor(VibSensor sensor);
    }
}
