package com.makers.vibapp.data.model;

import android.bluetooth.BluetoothDevice;

import com.makers.vibapp.data.SensorDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by Eliseo on 20/02/2016.
 */

public class VibSensor {

    String title;
    Boolean enabled;
    BluetoothDevice bluetoothDevice;

    public VibSensor(String title, Boolean enabled, BluetoothDevice bluetoothDevice) {
        this.title = title;
        this.enabled = enabled;
        this.bluetoothDevice = bluetoothDevice;
    }

    public VibSensor() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public BluetoothDevice getBluetoothDevice() {
        return bluetoothDevice;
    }

    public void setBluetoothDevice(BluetoothDevice bluetoothDevice) {
        this.bluetoothDevice = bluetoothDevice;
    }
}
