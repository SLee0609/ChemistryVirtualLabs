package com.seanlee.chemistryvirtuallabs.calorimetry.nh4no3.questions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.seanlee.chemistryvirtuallabs.R;

// activity where question 1 of calorimetry nh4no3 lab is explained
public class CalorimetryNh4no3Q1Exp extends AppCompatActivity {

    // represents how many questions the user gets correct
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorimetry_nh4no3_q1_exp);

        // get the gram, initialTemp, finalTemp, score values passed from the previous activity
        Bundle b = getIntent().getExtras();
        final double GRAM = b.getDouble("gram");
        final double INITIALTEMP = b.getDouble("initialTemp");
        final double FINALTEMP = b.getDouble("finalTemp");
        score = b.getInt("score");

        // next is used to go to CalorimetryCacl2Q2
        Button next = findViewById(R.id.next);

        // if next is clicked
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // go to CalorimetryNh4no3Q2 activity
                openCalorimetryNh4no3Q2(GRAM, INITIALTEMP, FINALTEMP, score);
                // finish activity so users cannot repeat questions multiple times
                finish();
            }
        });
    }

    // changes activity to CalorimetryNh4no3Q2
    public void openCalorimetryNh4no3Q2(double gram, double initialTemp, double finalTemp, int score) {
        Intent intent = new Intent(this, CalorimetryNh4no3Q2.class);
        // passes gram, initialTemp, finalTemp, and score to the new activity
        Bundle b = new Bundle();
        b.putDouble("gram", gram);
        b.putDouble("initialTemp", initialTemp);
        b.putDouble("finalTemp", finalTemp);
        b.putInt("score", score);
        intent.putExtras(b);
        startActivity(intent);
    }

    // if back button is clicked
    @Override
    public void onBackPressed() {
        // do nothing - prevents users from repeating questions multiple times
    }
}
