package com.seanlee.chemistryvirtuallabs.calorimetry.nh4no3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.seanlee.chemistryvirtuallabs.R;

// activity where users select the amount of nh4no3 to use in calorimetry
public class CalorimetryAmmoniumNitrate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorimetry_ammonium_nitrate);

        // grams is the text that displays the current progress on the selector
        final TextView grams = findViewById(R.id.grams);
        // selector allows users to choose number of grams to use
        final SeekBar selector = findViewById(R.id.gramSelector);
        // done is used to finalize gram selection
        Button done = findViewById(R.id.done);
        // warning is shown if user selects less than 1.0 grams - initially set to be invisible
        final TextView warning = findViewById(R.id.warning);

        // if selector progress changes
        selector.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // display current progress through grams
                // divide progress by 100 to allow grams to have two decimal places
                double currentGram = selector.getProgress() / 100.0;
                // make sure currentGram has two decimal places
                if (String.valueOf(currentGram).length() == 3) {
                    grams.setText(String.valueOf(currentGram) + "0 grams");
                } else {
                    grams.setText(String.valueOf(currentGram) + " grams");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // if done is clicked
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // chosenGram is the finalized value of grams
                final double chosenGram = selector.getProgress() / 100.0;

                // make sure chosenGram is above 1
                if (chosenGram >= 1) {
                    // go to CalorimetryCacl2Exp activity
                    openCalorimetryNh4no3Exp(chosenGram);
                } else {
                    // make warning visible
                    warning.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    // changes activity to CalorimetryNh4no3Exp
    public void openCalorimetryNh4no3Exp(double chosenGram) {
        Intent intent = new Intent(this, CalorimetryNh4no3Exp.class);
        // passes finalized gram value to the new activity
        Bundle b = new Bundle();
        b.putDouble("gram", chosenGram);
        intent.putExtras(b);
        startActivity(intent);
    }
}
