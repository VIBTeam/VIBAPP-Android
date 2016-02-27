package com.makers.vibapp.util;

import android.app.Service;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.makers.vibapp.data.model.VibNotification;
import com.makers.vibapp.util.pattern.VibPattern;
import com.zhaoxiaodan.miband.ActionCallback;
import com.zhaoxiaodan.miband.MiBand;
import com.zhaoxiaodan.miband.listeners.NotifyListener;
import com.zhaoxiaodan.miband.model.VibrationMode;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.UUID;

import co.lujun.lmbluetoothsdk.BluetoothController;
import co.lujun.lmbluetoothsdk.base.BluetoothListener;

public class VibService extends Service implements BluetoothListener {
    public static final String ACTION_START = "com.makers.vibapp.util.action.STARTSYSTEM";

    private BluetoothController mBTController;
    private OnBluetoothScanListener listener;
    private IBinder mBinder = new BluetoothServiceBinder();
    private MiBand miband;
    private BluetoothDevice miBandDevice;

    public VibService() {
        super();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        LogManager.d("Inicio del sistema");
        mBTController = BluetoothController.getInstance().build(getApplicationContext());
        mBTController.setAppUuid(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
        mBTController.setBluetoothListener(this);
        mBTController.connect("98:D3:31:70:69:7B");
        startMiBandSystem();
        return START_STICKY;
    }

    public class BluetoothServiceBinder extends Binder {
        public VibService getService() {
            LogManager.d("getService");
            return VibService.this;
        }
    }

    ArrayList<BluetoothDevice> bluetoothDevices = new ArrayList<>();

    @Override
    public IBinder onBind(Intent intent) {
        LogManager.d("onBind");
        return mBinder;
    }

    public void startScan(OnBluetoothScanListener listener) {
        this.listener = listener;
        mBTController.startScan();
    }

    public void stopScan() {
        mBTController.cancelScan();
    }

    @Override
    public void onReadData(BluetoothDevice device, byte[] data) {
        String code = new String(data, StandardCharsets.US_ASCII);
        LogManager.d("Recibidos Datos: " + code);
        if (code.startsWith("1")) {
            sendNotification(new VibNotification(4, 0));
        }
    }

    @Override
    public void onActionStateChanged(int preState, int state) {
        LogManager.d("onBluetoothServiceStateChanged" + preState + " " + state);
    }

    @Override
    public void onActionDiscoveryStateChanged(String discoveryState) {
    }

    @Override
    public void onActionScanModeChanged(int preScanMode, int scanMode) {

    }

    @Override
    public void onBluetoothServiceStateChanged(int state) {
        LogManager.d("onBluetoothServiceStateChanged" + state);
    }

    @Override
    public void onActionDeviceFound(BluetoothDevice device) {
        if (listener != null) {
            listener.onDeviceFound(device);
        }
        bluetoothDevices.add(device);
    }

    public ArrayList<BluetoothDevice> getBluetoothDevices(){
        return bluetoothDevices;
    }

    public interface OnBluetoothScanListener {
        void onDeviceFound(BluetoothDevice device);
    }

    private void startMiBandSystem() {
        miband = new MiBand(this);

        final ScanCallback scanCallback = new ScanCallback() {
            @Override
            public void onScanResult(int callbackType, ScanResult result) {
                BluetoothDevice device = result.getDevice();
                if (device.getAddress().startsWith("C8:0F:10:09:3D:65")) {
                    miBandDevice = device;
                    MiBand.stopScan(this);
                    connectMiBand();
                }
            }
        };
        MiBand.startScan(scanCallback);
        miband.setDisconnectedListener(new NotifyListener() {
            @Override
            public void onNotify(byte[] data) {
                connectMiBand();
            }
        });
    }

    public void connectMiBand(){
        miband.connect(miBandDevice, new ActionCallback() {
            @Override
            public void onSuccess(Object data) {
                LogManager.d("connect success");
            }

            @Override
            public void onFail(int errorCode, String msg) {
                LogManager.d("connect fail, code:" + errorCode + ",mgs:" + msg);
            }
        });
    }

    public void sendNotification(VibNotification notification) {
        miband.setLedColor(VibPattern.getLedColor(notification.getLedColor()));
        VibPattern.getVibPattern(notification.getVibrationPattern(), miband).run();
    }
}
