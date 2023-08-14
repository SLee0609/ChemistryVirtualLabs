package com.seanlee.chemistryvirtuallabs.calorimetry.nh4no3.questions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.seanlee.chemistryvirtuallabs.R;

// activity where question 3 of calorimetry nh4no3 lab is explained
public class CalorimetryNh4no3Q3Exp extends AppCompatActivity {

    // represents how many questions the user gets correct
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorimetry_nh4no3_q3_exp);

        // get the gram, initialTemp, finalTemp, score, ans values passed from the previous activity
        Bundle b = getIntent().getExtras();
        final double GRAM = b.getDouble("gram");
        final double INITIALTEMP = b.getDouble("initialTemp");
        final double FINALTEMP = b.getDouble("finalTemp");
        score = b.getInt("score");
        final double answer = b.getDouble("ans");

        // text explaining the answer
        TextView q3exp = findViewById(R.id.q3exp);

        String ans = "+" + answer + " kJ";

        // make sure gram has two decimal places
        String gram = "";
        if (String.valueOf(GRAM).length() == 3) {
            gram = String.valueOf(GRAM) + "0 g";
        } else {
            gram = String.valueOf(GRAM) + " g";
        }

        // mass of water (g)
        final double WATERMASS = 100.0;
        // specific heat for water (J / g*Celsius)
        final double WATERHEAT = 4.184;
        double q2ans = -(WATERHEAT * WATERMASS * (FINALTEMP - INITIALTEMP)) / 1000.0;
        // round to 3 sig figs
        if (q2ans < 1) {
            q2ans = Math.round(q2ans * 1000.0) / 1000.0;
        } else {
            q2ans = Math.round(q2ans * 100.0) / 100.0;
        }
        // make sure q2ans has two decimal places
        if (String.valueOf(q2ans).length() == 3) {
            q3exp.setText("The answer is " + ans + "\nBecause we assume that there is only heat transfer between water and the compound, q of water = -q of the compound\'s dissolution.\nUsing the answer from question 2, -" + q2ans + "0 kJ = -ΔHsolution * " + gram + " * (1 mol / 80.04 g)\nΔHsolution = " + ans);
        } else {
            q3exp.setText("The answer is " + ans + "\nBecause we assume that there is only heat transfer between water and the compound, q of water = -q of the compound\'s dissolution.\nUsing the answer from question 2, -" + q2ans + " kJ = -ΔHsolution * " + gram + " * (1 mol / 80.04 g)\nΔHsolution = " + ans);
        }

        // next used to go to CalorimetryNh4no3Q4
        Button next = findViewById(R.id.next);

        // if next is clicked
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // go to CalorimetryNh4no3Q4 activity
                openCalorimetryNh4no3Q4(GRAM, INITIALTEMP, FINALTEMP, score);
                // finish activity so users cannot repeat questions multiple times
                finish();
            }
        });
    }

    // changes activity to CalorimetryNh4no3Q4
    public void openCalorimetryNh4no3Q4(double gram, double initialTemp, double finalTemp, int score) {
        Intent intent = new Intent(this, CalorimetryNh4no3Q4.class);
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
