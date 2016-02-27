package com.makers.vibapp.activity;

import com.makers.vibapp.data.model.VibSensor;

/**
 * Created by Eliseo on 22/02/2016.
 */
public interface ActivityInterface {

    void showSensor();

    void showNotification();

    void showAlarms();

    void showSensorEditor(VibSensor sensor);

}
