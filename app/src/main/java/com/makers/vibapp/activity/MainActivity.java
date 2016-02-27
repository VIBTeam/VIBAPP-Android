package com.makers.vibapp.activity;

import android.app.FragmentManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.makers.vibapp.R;
import com.makers.vibapp.data.model.VibNotification;
import com.makers.vibapp.data.model.VibSensor;
import com.makers.vibapp.data.model.VibSoundType;
import com.makers.vibapp.fragment.NotificationEditorFragment;
import com.makers.vibapp.fragment.NotificationFragment;
import com.makers.vibapp.fragment.SensorEditorFragment;
import com.makers.vibapp.fragment.SensorFragment;
import com.makers.vibapp.util.VibService;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        SensorFragment.OnSensorClickListener,
        SensorEditorFragment.InteractionListener,
        NotificationFragment.InteractionListener, NotificationEditorFragment.InteractionListener {

    public VibService vibService;

    private ServiceConnection bluetoothConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            vibService = ((VibService.BluetoothServiceBinder) iBinder).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            vibService = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupView();
        showSensor();
        startService(new Intent(this, VibService.class));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, VibService.class);
        bindService(intent, bluetoothConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(bluetoothConnection);
    }

    private void setupView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_sensor) {
            showSensor();
        } else if (id == R.id.nav_notification) {
            showNotification();
        } else if (id == R.id.nav_alarm) {
            showAlarms();
        } else if (id == R.id.nav_settings) {
            showNotification();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void showSensor() {
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.popBackStackImmediate();
        SensorFragment fragment = SensorFragment.newInstance();
        fragmentManager.beginTransaction().replace(R.id.frameLayoutMainContainer, fragment).commit();
    }

    public void showNotification() {
        FragmentManager fragmentManager = getFragmentManager();
        NotificationFragment fragment = NotificationFragment.newInstance();
        fragmentManager.beginTransaction().replace(R.id.frameLayoutMainContainer, fragment).commit();
    }

    public void showAlarms() {

    }

    public void showSensorEditor(VibSensor sensor) {
        FragmentManager fragmentManager = getFragmentManager();
        SensorEditorFragment fragment = SensorEditorFragment.newInstance(sensor);
        fragmentManager.beginTransaction().replace(R.id.frameLayoutMainContainer, fragment).commit();
    }

    public void showSensorAdder() {
        FragmentManager fragmentManager = getFragmentManager();
        SensorEditorFragment fragment = SensorEditorFragment.newInstance();
        fragmentManager.beginTransaction().replace(R.id.frameLayoutMainContainer, fragment).commit();
    }

    @Override
    public void onSensorClick(VibSensor sensor) {
        showSensorEditor(sensor);
    }

    @Override
    public void onAddItemButton() {
        showSensorAdder();
    }

    @Override
    public void onSaveSensor(VibSensor sensor) {

    }

    @Override
    public void showNotificationAdder() {
        FragmentManager fragmentManager = getFragmentManager();
        NotificationEditorFragment fragment = NotificationEditorFragment.newInstance();
        fragmentManager.beginTransaction().replace(R.id.frameLayoutMainContainer, fragment).commit();
    }

    @Override
    public void showNotificationEditor(VibSoundType vibSoundType) {
        FragmentManager fragmentManager = getFragmentManager();
        NotificationEditorFragment fragment = NotificationEditorFragment.newInstance();
        fragmentManager.beginTransaction().replace(R.id.frameLayoutMainContainer, fragment).commit();
    }

    @Override
    public void onSaveNotification(VibNotification vibNotification) {

    }
}