package com.seanlee.chemistryvirtuallabs.calorimetry.cacl2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.seanlee.chemistryvirtuallabs.R;

// activity where users see calorimetry lab for cacl2
public class CalorimetryCacl2Exp extends AppCompatActivity {

    // text that displays the current temperature of water
    private TextView temp;
    // button used to start temperature changes
    private Button start;
    // button used to go to CalorimetryCacl2Stats activity - initially set to gone visibility
    private Button next;

    // value of initial temperature of water (Celsius)
    private final double INITIALTEMP = 25.0;
    // value of the current temperature of water (Celsius)
    private double currentTemp = INITIALTEMP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorimetry_cacl2_exp);

        temp = findViewById(R.id.currentTemp);
        start = findViewById(R.id.start);
        next = findViewById(R.id.next);

        // get the gram value that the users previously selected
        Bundle b = getIntent().getExtras();
        final double GRAM = b.getDouble("gram");

        // cacl2 heat of solution in water at 25 degrees Celsius (kJ / mol)
        final double CACL2HEAT = -82.8;
        // molar mass of cacl2 (g / mol)
        final double CACL2MM = 110.98;
        // mass of water (g)
        final double WATERMASS = 100.0;
        // specific heat for water (J / g*Celsius)
        final double WATERHEAT = 4.184;
        // temperature change of water (Celsius) - rounded to one decimal place
        final double TEMPCHANGE = (Math.round(((CACL2HEAT * GRAM * (1 / CACL2MM)) / (-WATERMASS * WATERHEAT * 0.001)) * 10) / 10.0) - 0.1;
        // final temperature of water (Celsius) - rounded to one decimal place
        final double FINALTEMP = INITIALTEMP + TEMPCHANGE;

        // set initial temp as text
        temp.setText(currentTemp + " " + (char)176 + "C");

        // if start is clicked
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        try {
                            // loop until currentTemp reaches FINALTEMP
                            while (currentTemp != FINALTEMP) {
                                // sleep for one second
                                Thread.sleep(1000);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        // update currentTemp - cut to one decimal place
                                        if (checkRound(TEMPCHANGE / 9) == 0) {
                                            currentTemp = checkRound(currentTemp + 0.1);
                                        } else {
                                            currentTemp = checkRound(currentTemp + TEMPCHANGE / 9);
                                        }

                                        // if currentTemp exceeds FINALTEMP
                                        if (currentTemp > FINALTEMP) {
                                            // set currentTemp to FINALTEMP
                                            currentTemp = FINALTEMP;
                                            // make start button invisible
                                            start.setVisibility(View.INVISIBLE);
                                            // make next button visible
                                            next.setVisibility(View.VISIBLE);
                                        }

                                        // update display of temperature
                                        temp.setText(currentTemp + " " + (char)176 + "C");
                                    }
                                });
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };
                thread.start();
            }
        });

        // if next is clicked
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // go to CalorimetryCacl2Stats activity
                openCalorimetryCacl2Stats(GRAM, INITIALTEMP, FINALTEMP);
            }
        });
    }

    // returns a double cut to one decimal place
    public double checkRound(double num) {
        // str is a string representing the double
        String str = String.valueOf(num);

        // index of the decimal point
        int index = 0;
        // loop through the string and locate the decimal point
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 46) { // 46 = ascii for '.'
                // update index
                index = i;
                break;
            }
        }
        // cut the string after the digit to the right of the decimal point
        str = str.substring(0, index + 2);
        // return the string as a double
        return Double.valueOf(str);
    }

    // changes activity to CalorimetryCacl2Stats
    public void openCalorimetryCacl2Stats(double gram, double initialTemp, double finalTemp) {
        Intent intent = new Intent(this, CalorimetryCacl2Stats.class);
        // passes gram, initialTemp, and finalTemp, status, and score to the new activity
        Bundle b = new Bundle();
        b.putDouble("gram", gram);
        b.putDouble("initialTemp", initialTemp);
        b.putDouble("finalTemp", finalTemp);
        // status = 0 in data table activity if coming from the experiment activity
        b.putInt("status", 0);
        // score = 0 initially
        b.putInt("score", 0);
        intent.putExtras(b);
        startActivity(intent);
    }
}
