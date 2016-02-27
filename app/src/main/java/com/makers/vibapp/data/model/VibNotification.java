package com.makers.vibapp.data.model;

import com.makers.vibapp.data.SensorDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by Eliseo on 20/02/2016.
 */
//@Table(database = SensorDatabase.class)
public class VibNotification //extends BaseModel
{

    //@PrimaryKey
    String id;

    //@Column
    int vibrationPattern;

    //@Column
    int ledColor;

    public VibNotification(int vibrationPattern, int ledColor) {
        this.vibrationPattern = vibrationPattern;
        this.ledColor = ledColor;
    }

    public VibNotification() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getVibrationPattern() {
        return vibrationPattern;
    }

    public void setVibrationPattern(int vibrationPattern) {
        this.vibrationPattern = vibrationPattern;
    }

    public int getLedColor() {
        return ledColor;
    }

    public void setLedColor(int ledColor) {
        this.ledColor = ledColor;
    }
}
