package com.guy.class23b_and_4;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.widget.Toast;

import java.text.DecimalFormat;

public class StepDetector {

    public interface CallBack_StepDetector {
        void stepDetect();
        void yemanStepDetect();
    }


    private SensorManager mySensorManager;
    private Sensor sensor;
    private int sensorType = Sensor.TYPE_GYROSCOPE;
    private CallBack_StepDetector callBack_stepDetector;

    public StepDetector(Context context, CallBack_StepDetector callBack_stepDetector) {
        this.callBack_stepDetector = callBack_stepDetector;
        mySensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sensor = mySensorManager.getDefaultSensor(sensorType);
        if (sensor == null) {
            Toast.makeText(context, "No Sensor!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Maximum Range: " + String.valueOf(sensor.getMaximumRange()), Toast.LENGTH_SHORT).show();
        }
    }

    public void start() {
        mySensorManager.registerListener(sensorListener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    public void stop() {
        mySensorManager.unregisterListener(sensorListener);
    }

    SensorEventListener sensorListener = new SensorEventListener() {

        long last = 0;
        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() == sensorType) {
                if (System.currentTimeMillis() < last + 2000) {
                    return;
                }


                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];
                String str = new DecimalFormat("##.##").format(x) + "\n"
                        + new DecimalFormat("##.##").format(y) + "\n"
                        + new DecimalFormat("##.##").format(z) + "\n";
                //Log.d("pttt", System.currentTimeMillis() + " - " + event.values[0]);

                double value = Math.sqrt(x*x + y*y + z*z);
                Log.d("pttt", System.currentTimeMillis() + " - " + value);
                if (value > 4f) {
                    last = System.currentTimeMillis();
                    callBack_stepDetector.yemanStepDetect();
                } else if (value > 2f) {
                    last = System.currentTimeMillis();
                    callBack_stepDetector.stepDetect();
                }
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }

    };
}
