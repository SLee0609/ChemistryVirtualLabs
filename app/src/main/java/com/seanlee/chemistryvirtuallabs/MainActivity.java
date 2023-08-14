package com.seanlee.chemistryvirtuallabs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.seanlee.chemistryvirtuallabs.calorimetry.CalorimetryIntro;

// home activity where users select the lab they want to do
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // button used to select calorimetry lab option
        Button calorimetryButton = findViewById(R.id.calorimetryButton);

        // if calorimetryButton is clicked
        calorimetryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // go to CalorimetryIntro activity
                openCalorimetryIntro();
            }
        });
    }

    // changes activity to CalorimetryIntro
    public void openCalorimetryIntro() {
        Intent intent = new Intent(this, CalorimetryIntro.class);
        startActivity(intent);
    }
}
