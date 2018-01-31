package com.hellfire.user.sensortouch;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class Grav extends AppCompatActivity {

    private SensorManager msensorManager;
    private Sensor compassSensor;
    private SensorEventListener compassSensorListener;
    private ImageView image;
    private float currDeg = 0f;
    private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grav);

        image = (ImageView)findViewById(R.id.compassImg);
        txt = (TextView)findViewById(R.id.degree);
        msensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        compassSensor = msensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        compassSensorListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                float deg = Math.round(sensorEvent.values[0]);
                txt.setText(Float.toString(deg)+" degrees");
                RotateAnimation ra = new RotateAnimation(currDeg, -deg, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                ra.setDuration(200);
                ra.setFillAfter(true);
                image.startAnimation(ra);
                currDeg = currDeg - deg;
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };

    }

    @Override
    protected void onResume(){
        super.onResume();
        msensorManager.registerListener(compassSensorListener, compassSensor, SensorManager.SENSOR_DELAY_GAME);

    }

    @Override
    protected void onPause(){
        super.onPause();
        msensorManager.unregisterListener(compassSensorListener);
    }
}
