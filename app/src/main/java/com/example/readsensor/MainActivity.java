package com.example.readsensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * MainActivity is the main entry point of the application.
 * It displays the accelerometer sensor data on the screen.
 */
public class MainActivity extends AppCompatActivity implements SensorEventListener {

    // Sensor manager to interact with device sensors.
    private SensorManager sensorManager;
    // Accelerometer sensor object.
    private Sensor accelerometer;
    // TextView to display the sensor data.
    private TextView sensorDataTextView;

    /**
     * Called when the activity is first created.
     * This is where you should do all of your normal static set up:
     * create views, bind data to lists, etc.
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in onSaveInstanceState(Bundle).
     *     Note: Otherwise it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the TextView from the layout.
        sensorDataTextView = findViewById(R.id.sensor_data);
        // Get an instance of the sensor service.
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        // Get the accelerometer sensor.
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    /**
     * Called when the activity will start interacting with the user.
     * At this point your activity is at the top of the activity stack,
     * with user input going to it.
     */
    @Override
    protected void onResume() {
        super.onResume();
        // Register the sensor listener to receive accelerometer data.
        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    /**
     * Called when the activity is no longer interacting with the user.
     * This is a good place to stop animations or other ongoing actions
     * that may be consuming CPU.
     */
    @Override
    protected void onPause() {
        super.onPause();
        // Unregister the sensor listener to save battery.
        sensorManager.unregisterListener(this);
    }

    /**
     * Called when the accuracy of the registered sensor has changed.
     * @param sensor The Sensor that has changed.
     * @param accuracy The new accuracy of this sensor.
     */
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // This method is not used in this application.
    }

    /**
     * Called when there is a new sensor event.
     * @param event The SensorEvent.
     */
    @Override
    public void onSensorChanged(SensorEvent event) {
        // Check if the sensor event is from the accelerometer.
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            // Get the accelerometer values.
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            // Create the string to display the sensor data.
            String sensorData = "X: " + x + "\nY: " + y + "\nZ: " + z;

            // Set the text of the TextView to the sensor data.
            sensorDataTextView.setText(sensorData);
        }
    }
}
