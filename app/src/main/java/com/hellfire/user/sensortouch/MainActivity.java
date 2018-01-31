package com.hellfire.user.sensortouch;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    //Button btnAcc, btnGrav, btnProx;
    //TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //btnAcc=findViewById(R.id.accelerometer);
        //btnGrav=findViewById(R.id.gravity);
        //btnProx=findViewById(R.id.proximity);
        //txt=findViewById(R.id.text);
    }

    public void onClickAcc(android.view.View view){
        Intent intenta;
        intenta = new Intent(this, Acc.class);
        startActivity(intenta);
    }

    public void onClickGrav(android.view.View v) {
        Intent intentg;
        intentg = new Intent(this, Grav.class);
        startActivity(intentg);
    }

    public void onClickProx(android.view.View v) {
        Intent intentp;
        intentp = new Intent(this, Prox.class);
        startActivity(intentp);
    }

}
