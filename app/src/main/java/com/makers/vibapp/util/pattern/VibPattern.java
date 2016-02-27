package com.makers.vibapp.util.pattern;

import com.zhaoxiaodan.miband.MiBand;
import com.zhaoxiaodan.miband.model.LedColor;
import com.zhaoxiaodan.miband.model.VibrationMode;

/**
 * Created by Eliseo on 27/02/2016.
 */
public class VibPattern {

    public static Runnable getVibPattern(final int id, final MiBand miBand){
        switch (id){
            case 0:
                return new Runnable() {
                    @Override
                    public void run() {
                        try {
                            miBand.startVibration(VibrationMode.VIBRATION_WITH_LED);
                            Thread.sleep(1500);
                            miBand.stopVibration();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };
            default:
                return new Runnable() {
                    @Override
                    public void run() {
                        try {
                            for(int i = 0; i < id; i++) {
                                miBand.startVibration(VibrationMode.VIBRATION_WITH_LED);
                                Thread.sleep(100);
                                miBand.stopVibration();
                                Thread.sleep(50);
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };
        }
    }

    public static LedColor getLedColor(final int id){
        switch (id){
            case 0:
                return LedColor.BLUE;
            case 1:
                return LedColor.GREEN;
            case 2:
                return LedColor.ORANGE;
            case 3:
                return LedColor.RED;
            default:
                return LedColor.BLUE;
        }
    }
}
