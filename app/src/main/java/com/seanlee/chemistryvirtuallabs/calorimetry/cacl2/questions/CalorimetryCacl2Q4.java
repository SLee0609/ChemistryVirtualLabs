package com.seanlee.chemistryvirtuallabs.calorimetry.cacl2.questions;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.seanlee.chemistryvirtuallabs.R;
import com.seanlee.chemistryvirtuallabs.calorimetry.cacl2.CalorimetryCacl2Stats;

// activity where users answer question 4 of calorimetry cacl2 lab
public class CalorimetryCacl2Q4 extends AppCompatActivity {

    // answer is where users submit their answer
    private EditText answer;
    // check and x are initially set to invisible
    private ImageView check;
    private ImageView x;
    // next used to go to CalorimetryCacl2Q3Exp - initially set to invisible
    private Button next;

    // represents how many questions the user gets correct
    private int score;

    // done is used to check if the user answered already
    private boolean done = false;

    // correct answer for this question
    private double correctAns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorimetry_cacl2_q4);

        // get the gram, initialTemp, finalTemp, score values passed from the previous activity
        Bundle b = getIntent().getExtras();
        final double GRAM = b.getDouble("gram");
        final double INITIALTEMP = b.getDouble("initialTemp");
        final double FINALTEMP = b.getDouble("finalTemp");
        score = b.getInt("score");

        answer = findViewById(R.id.q4answer);
        check = findViewById(R.id.check);
        x = findViewById(R.id.x);
        next = findViewById(R.id.next);

        // submit is used to submit answer
        Button submit = findViewById(R.id.submit);

        // back is used to go back to data table
        final Button back = findViewById(R.id.datatable);

        // cacl2 heat of solution in water at 25 degrees Celsius (kJ / mol)
        final double CACL2HEAT = -82.8;
        // molar mass of cacl2 (g / mol)
        final double CACL2MM = 110.98;
        // mass of water (g)
        final double WATERMASS = 100.0;
        // specific heat for water (J / g*Celsius)
        final double WATERHEAT = 4.184;

        // correctAns is the correct answer for this question (%)
        correctAns = ((CACL2HEAT + (((WATERHEAT * WATERMASS * (FINALTEMP - INITIALTEMP)) / 1000.0) / (GRAM / CACL2MM))) * 100.0) / CACL2HEAT;
        // round to 3 sig figs
        if (correctAns >= 10) {
            correctAns = Math.round(correctAns * 10.0) / 10.0;
        } else if (correctAns < 1) {
            correctAns = Math.round(correctAns * 1000.0) / 1000.0;
        } else {
            correctAns = Math.round(correctAns * 100.0) / 100.0;
        }

        // if back is clicked
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // go to CalorimetryCacl2Stats activity
                openCalorimetryCacl2Stats(GRAM, INITIALTEMP, FINALTEMP, score);
            }
        });

        // if submit is clicked
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!done) {
                    // set view visibility to invisible
                    back.setVisibility(View.INVISIBLE);
                    // ans is the answer the user submitted
                    String ans = answer.getText().toString().replaceAll(" ", "");
                    // if ans is correct
                    if (!ans.equals("") && Double.valueOf(ans) == correctAns) {
                        // make check visible
                        check.setVisibility(View.VISIBLE);
                        // increment score
                        score++;
                    } else { // if ans is not correct
                        // make x visible
                        x.setVisibility(View.VISIBLE);
                    }
                    // make next visible
                    next.setVisibility(View.VISIBLE);
                }
                // update done
                done = true;
            }
        });

        // if next is clicked
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // go to CalorimetryCacl2Q4Exp activity
                openCalorimetryCacl2Q4Exp(GRAM, INITIALTEMP, FINALTEMP, score, correctAns);
                // finish activity so users cannot repeat questions multiple times
                finish();
            }
        });
    }

    // changes activity to CalorimetryCacl2Q4Exp
    public void openCalorimetryCacl2Q4Exp(double gram, double initialTemp, double finalTemp, int score, double correctAns) {
        Intent intent = new Intent(this, CalorimetryCacl2Q4Exp.class);
        // passes gram, initialTemp, finalTemp, score, and correctAns to the new activity
        Bundle b = new Bundle();
        b.putDouble("gram", gram);
        b.putDouble("initialTemp", initialTemp);
        b.putDouble("finalTemp", finalTemp);
        b.putInt("score", score);
        b.putDouble("ans", correctAns);
        intent.putExtras(b);
        startActivity(intent);
    }

    // if back button is clicked
    @Override
    public void onBackPressed() {
        // do nothing - prevents users from repeating questions multiple times
    }

    // changes activity to CalorimetryCacl2Stats
    public void openCalorimetryCacl2Stats(double gram, double initialTemp, double finalTemp, int score) {
        Intent intent = new Intent(this, CalorimetryCacl2Stats.class);
        // passes gram, initialTemp, and finalTemp, status, and score to the new activity
        Bundle b = new Bundle();
        b.putDouble("gram", gram);
        b.putDouble("initialTemp", initialTemp);
        b.putDouble("finalTemp", finalTemp);
        // status = 4 in data table activity if coming from Q4 activity
        b.putInt("status", 4);
        b.putInt("score", score);
        intent.putExtras(b);
        startActivity(intent);
    }
}
