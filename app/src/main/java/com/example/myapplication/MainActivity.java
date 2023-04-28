 package com.example.myapplication;

 import android.app.Activity;
 import android.hardware.Sensor;
 import android.hardware.SensorEvent;
 import android.hardware.SensorEventListener;
 import android.hardware.SensorManager;
 import android.os.Bundle;
 import android.widget.RelativeLayout;
 import android.widget.TextView;

 public class MainActivity extends Activity implements SensorEventListener {

     private SensorManager sensorManager;
     private Sensor lightSensor;
     private TextView luxLabel;
     private TextView brightnessLabel;
     private RelativeLayout background;

     @Override
     public void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_main);

         sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
         lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

         luxLabel = (TextView) findViewById(R.id.luxLabel);
         brightnessLabel = (TextView) findViewById(R.id.brightnessLabel);
         background = (RelativeLayout) findViewById(R.id.background);
     }

     @Override
     public void onResume() {
         super.onResume();
         sensorManager.registerListener(this, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
     }

     @Override
     public void onPause() {
         super.onPause();
         sensorManager.unregisterListener(this);
     }

     @Override
     public void onSensorChanged(SensorEvent event) {
         float lux = event.values[0];

         luxLabel.setText(String.format("Lux level: %.2f", lux));

         if (lux < 500) {
             brightnessLabel.setText("Low Brightness");
             brightnessLabel.setTextColor(getResources().getColor(android.R.color.white));
             background.setBackgroundColor(getResources().getColor(android.R.color.black));
         } else if (lux >= 500 && lux < 1000) {
             brightnessLabel.setText("Moderate Brightness");
             brightnessLabel.setTextColor(getResources().getColor(android.R.color.white));
             background.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
         } else {
             brightnessLabel.setText("High Brightness");
             brightnessLabel.setTextColor(getResources().getColor(android.R.color.black));
             background.setBackgroundColor(getResources().getColor(android.R.color.white));
         }
     }

     @Override
     public void onAccuracyChanged(Sensor sensor, int accuracy) {
         // Do nothing for now
     }
 }