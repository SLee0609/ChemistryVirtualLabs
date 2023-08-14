package com.seanlee.chemistryvirtuallabs.calorimetry.nh4no3.questions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.seanlee.chemistryvirtuallabs.R;
import com.seanlee.chemistryvirtuallabs.calorimetry.CalorimetryFinal;

// activity where question 4 of calorimetry nh4no3 lab is explained
public class CalorimetryNh4no3Q4Exp extends AppCompatActivity {

    // represents how many questions the user gets correct
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorimetry_nh4no3_q4_exp);

        // get the gram, initialTemp, finalTemp, score, ans values passed from the previous activity
        Bundle b = getIntent().getExtras();
        final double GRAM = b.getDouble("gram");
        final double INITIALTEMP = b.getDouble("initialTemp");
        final double FINALTEMP = b.getDouble("finalTemp");
        score = b.getInt("score");
        final double answer = b.getDouble("ans");

        // text explaining the answer
        TextView q4exp = findViewById(R.id.q4exp);

        // molar mass of nh4no3 (g / mol)
        final double NH4NO3MM = 80.04;
        // mass of water (g)
        final double WATERMASS = 100.0;
        // specific heat for water (J / g*Celsius)
        final double WATERHEAT = 4.184;

        // q3ans is the answer for q3 (kJ / mol)
        double q3ans = -(((WATERHEAT * WATERMASS * (FINALTEMP - INITIALTEMP)) / 1000.0) / (GRAM / NH4NO3MM));
        // round to 3 sig figs
        q3ans = Math.round(q3ans * 10.0) / 10.0;

        if (String.valueOf(q3ans).length() == 3) {
            q4exp.setText("The answer is " + answer + "%\nUsing the answer from question 3, percent error = |(" + q3ans + "0)-(25.7)) / (25.7)| * 100%\nPercent error = " + answer + "%\nIt is common to get a heat of solution smaller in magnitude than the actual value. This is because some of the heat in the atmosphere is absorbed by the water. Thus, the water temperature decreases less than it should, and you get a smaller calculated heat of solution.");
        } else {
            q4exp.setText("The answer is " + answer + "%\nUsing the answer from question 3, percent error = |(" + q3ans + ")-(25.7)) / (25.7)| * 100%\nPercent error = " + answer + "%\nIt is common to get a heat of solution smaller in magnitude than the actual value. This is because some of the heat in the atmosphere is absorbed by the water. Thus, the water temperature decreases less than it should, and you get a smaller calculated heat of solution.");
        }

        // next used to go to CalorimetryFinal
        Button next = findViewById(R.id.next);

        // if next is clicked
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // go to CalorimetryFinal activity
                openCalorimetryFinal(score);
                // finish activity so users cannot repeat questions multiple times
                finish();
            }
        });
    }

    // changes activity to CalorimetryFinal
    public void openCalorimetryFinal(int score) {
        Intent intent = new Intent(this, CalorimetryFinal.class);
        // passes score to the new activity
        Bundle b = new Bundle();
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
