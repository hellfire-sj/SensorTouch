package com.hellfire.user.sensortouch;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Acc extends AppCompatActivity {

    private SensorManager msensorManager;
    private Sensor accSensor;
    private SensorEventListener accSensorListener;
    private static float threshold = 15.0f;
    private float a, x, y, z;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acc);

        msensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        accSensor = msensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        accSensorListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                x = sensorEvent.values[0];
                y = sensorEvent.values[1];
                z = sensorEvent.values[2];
                a = (float)Math.sqrt(x*x + y*y + z*z);
                if(a > threshold){
                    getWindow().getDecorView().setBackgroundColor(Color.BLACK);
                }
                else
                {
                    getWindow().getDecorView().setBackgroundColor(Color.WHITE);
                }
                //if(sensorEvent.values[2]>.5f){
                //    getWindow().getDecorView().setBackgroundColor(Color.BLACK);
                //}
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
    }



    @Override
    protected void onResume(){
        super.onResume();
        msensorManager.registerListener(accSensorListener, accSensor, 2*1000*1000);

    }

    @Override
    protected void onPause(){
        super.onPause();
        msensorManager.unregisterListener(accSensorListener);
    }

}

