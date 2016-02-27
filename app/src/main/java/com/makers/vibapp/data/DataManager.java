package com.makers.vibapp.data;

import com.makers.vibapp.R;
import com.makers.vibapp.data.model.VibSensor;
import com.makers.vibapp.data.model.VibSoundType;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Eliseo on 26/02/2016.
 */
public class DataManager {

    public static DataManager instance;
    public static ArrayList<VibSensor> sensors;

    public DataManager() {
        sensors = new ArrayList<>();
    }

    public static DataManager getInstance(){
        if(instance == null)
            instance = new DataManager();
        return instance;
    }


    public ArrayList<VibSoundType> getSoundTypes() {
        ArrayList<VibSoundType> arrayList = new ArrayList<>();
        arrayList.add(new VibSoundType(R.drawable.sound_baby, "Bebé"));
        arrayList.add(new VibSoundType(R.drawable.sound_bell, "Alarma"));
        arrayList.add(new VibSoundType(R.drawable.sound_dog, "Perro"));
        arrayList.add(new VibSoundType(R.drawable.sound_fire, "Fuego"));
        arrayList.add(new VibSoundType(R.drawable.sound_microwave, "Microhondas"));
        arrayList.add(new VibSoundType(R.drawable.sound_phone, "Teléfono"));
        arrayList.add(new VibSoundType(R.drawable.sound_saucepan, "Olla"));
        arrayList.add(new VibSoundType(R.drawable.sound_washing_machine, "Lavadora"));
        return arrayList;
    }

    public void addVibSensor(VibSensor sensor){
        sensors.add(sensor);
    }

    public ArrayList<VibSensor> getVibSensors(){
        return sensors;
    }

    public VibSensor getVibSensorsById(String sensorId) {
        for(VibSensor sensor : sensors){
            if(sensor.getBluetoothDevice().getAddress().equals(sensorId)){
                return sensor;
            }
        }
        return null;
    }
}
