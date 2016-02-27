package com.makers.vibapp.data;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by Eliseo on 20/02/2016.
 */
@Database(name = SensorDatabase.NAME, version = SensorDatabase.VERSION)
public class SensorDatabase {
    public static final String NAME = "SensorDatabase";
    public static final int VERSION = 1;
}
