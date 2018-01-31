package com.hellfire.user.sensortouch;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class Prox extends AppCompatActivity {

    private SensorManager msensorManager;
    private Sensor proximitySensor;
    private SensorEventListener proximitySensorListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prox);

        msensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        proximitySensor = msensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        /*
        if(proximitySensor == null){
            //Log.e(TAG, "Proximity sensor not available.");
            //finish();
        }
        */

        proximitySensorListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                if(sensorEvent.values[0] < proximitySensor.getMaximumRange()){
                    getWindow().getDecorView().setBackgroundColor(Color.MAGENTA);
                }
                else
                {
                    getWindow().getDecorView().setBackgroundColor(Color.BLUE);
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };


    }

    @Override
    protected void onResume(){
        super.onResume();
        msensorManager.registerListener(proximitySensorListener, proximitySensor, 2*1000*1000);

    }

    @Override
    protected void onPause(){
        super.onPause();
        msensorManager.unregisterListener(proximitySensorListener);
    }


}
