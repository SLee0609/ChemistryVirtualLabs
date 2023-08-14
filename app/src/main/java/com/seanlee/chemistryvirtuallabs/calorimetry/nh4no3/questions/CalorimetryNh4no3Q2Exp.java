package com.seanlee.chemistryvirtuallabs.calorimetry.nh4no3.questions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.seanlee.chemistryvirtuallabs.R;

// activity where question 2 of calorimetry nh4no3 lab is explained
public class CalorimetryNh4no3Q2Exp extends AppCompatActivity {

    // represents how many questions the user gets correct
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorimetry_nh4no3_q2_exp);

        // get the gram, initialTemp, finalTemp, score, ans values passed from the previous activity
        Bundle b = getIntent().getExtras();
        final double GRAM = b.getDouble("gram");
        final double INITIALTEMP = b.getDouble("initialTemp");
        final double FINALTEMP = b.getDouble("finalTemp");
        score = b.getInt("score");
        final double answer = b.getDouble("ans");

        // text explaining the answer
        TextView q2exp = findViewById(R.id.q2exp);

        // make sure answer has two decimal places
        String ansText = "";
        if (String.valueOf(answer).length() == 3) {
            ansText = "-" + answer + "0 kJ";
        } else {
            ansText = "-" + answer + " kJ";
        }

        q2exp.setText("The answer is " + ansText + "\nWe can use q = mc∆t, where m = mass of water, c = specific heat of water, and ∆t = final minus initial temperature of water.\nq = 100.0 g * 4.184 J/g°C * (" + FINALTEMP + " - " + INITIALTEMP + ")°C" + "\nq = -" + Math.round(answer * 1000) + " J\nq = " + ansText);

        // next used to go to CalorimetryNh4no3Q3
        Button next = findViewById(R.id.next);

        // if next is clicked
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // go to CalorimetryNh4no3Q3 activity
                openCalorimetryNh4no3Q3(GRAM, INITIALTEMP, FINALTEMP, score);
                // finish activity so users cannot repeat questions multiple times
                finish();
            }
        });
    }

    // changes activity to CalorimetryNh4no3Q3
    public void openCalorimetryNh4no3Q3(double gram, double initialTemp, double finalTemp, int score) {
        Intent intent = new Intent(this, CalorimetryNh4no3Q3.class);
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
