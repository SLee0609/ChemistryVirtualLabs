package com.seanlee.chemistryvirtuallabs.calorimetry.nh4no3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.seanlee.chemistryvirtuallabs.R;
import com.seanlee.chemistryvirtuallabs.calorimetry.nh4no3.questions.CalorimetryNh4no3Q1;
import com.seanlee.chemistryvirtuallabs.calorimetry.nh4no3.questions.CalorimetryNh4no3Q2;
import com.seanlee.chemistryvirtuallabs.calorimetry.nh4no3.questions.CalorimetryNh4no3Q3;
import com.seanlee.chemistryvirtuallabs.calorimetry.nh4no3.questions.CalorimetryNh4no3Q4;

// activity where users see the lab data for nh4no3
public class CalorimetryNh4no3Stats extends AppCompatActivity {

    // represents how many questions the user gets correct
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorimetry_nh4no3_stats);

        // get the gram, initialTemp, finalTemp, status, score values passed from the previous activity
        Bundle b = getIntent().getExtras();
        final double GRAM = b.getDouble("gram");
        final double INITIALTEMP = b.getDouble("initialTemp");
        final double FINALTEMP = b.getDouble("finalTemp");
        final int STATUS = b.getInt("status");
        score = b.getInt("score");

        // display the mass of the compound used
        TextView mass = findViewById(R.id.mass);
        // make sure GRAM has two decimal places
        if (String.valueOf(GRAM).length() == 3) {
            mass.setText(GRAM + "0 g");
        } else {
            mass.setText(GRAM + " g");
        }

        // display the initial temperature of water
        TextView initialTemp = findViewById(R.id.initialTemp);
        initialTemp.setText(INITIALTEMP + " " + (char)176 + "C");

        // display the final temperature of water
        TextView finalTemp = findViewById(R.id.finalTemp);
        finalTemp.setText(FINALTEMP + " " + (char)176 + "C");

        // button used to go to CalorimetryCacl2Q1 activity
        Button next = findViewById(R.id.next);
        // only set the visibility to visible if status is 0 (coming from experiment activity
        if (STATUS == 0) {
            next.setVisibility(View.VISIBLE);
        } else {
            next.setVisibility(View.INVISIBLE);
        }

        // button used to go back to question activities
        Button back = findViewById(R.id.back);
        // only set the visibility to visible if status is not 0 (coming from questions activities)
        if (STATUS == 0) {
            back.setVisibility(View.INVISIBLE);
        } else {
            back.setVisibility(View.VISIBLE);
        }

        // if next is clicked
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // go to CalorimetryNh4no3Q1 activity
                openCalorimetryNh4no3Q1(GRAM, INITIALTEMP, FINALTEMP, score);
            }
        });

        // if back is clicked
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // go back to the question activities
                if (STATUS == 1) {
                    openCalorimetryNh4no3Q1(GRAM, INITIALTEMP, FINALTEMP, score);
                } else if (STATUS == 2) {
                    openCalorimetryNh4no3Q2(GRAM, INITIALTEMP, FINALTEMP, score);
                } else if (STATUS == 3) {
                    openCalorimetryNh4no3Q3(GRAM, INITIALTEMP, FINALTEMP, score);
                } else if (STATUS == 4) {
                    openCalorimetryNh4no3Q4(GRAM, INITIALTEMP, FINALTEMP, score);
                }
            }
        });
    }

    // changes activity to CalorimetryNh4no3Q1
    public void openCalorimetryNh4no3Q1(double gram, double initialTemp, double finalTemp, int score) {
        Intent intent = new Intent(this, CalorimetryNh4no3Q1.class);
        // passes gram, initialTemp, finalTemp, and score to the new activity
        Bundle b = new Bundle();
        b.putDouble("gram", gram);
        b.putDouble("initialTemp", initialTemp);
        b.putDouble("finalTemp", finalTemp);
        b.putInt("score", score);
        intent.putExtras(b);
        startActivity(intent);
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
