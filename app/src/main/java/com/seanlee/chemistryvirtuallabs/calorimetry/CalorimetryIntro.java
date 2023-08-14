package com.seanlee.chemistryvirtuallabs.calorimetry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.seanlee.chemistryvirtuallabs.R;

// activity that explains the calorimetry lab
public class CalorimetryIntro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorimetry_intro);

        // button used to go to CalorimetryChooseCompound activity
        Button start = findViewById(R.id.start);

        // if start is clicked
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // go to CalorimetryChooseCompound activity
                openCalorimetryChooseCompound();
            }
        });
    }

    // changes activity to CalorimetryChooseCompound
    public void openCalorimetryChooseCompound() {
        Intent intent = new Intent(this, CalorimetryChooseCompound.class);
        startActivity(intent);
    }
}
