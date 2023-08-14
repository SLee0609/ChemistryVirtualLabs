package com.seanlee.chemistryvirtuallabs.calorimetry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.seanlee.chemistryvirtuallabs.R;
import com.seanlee.chemistryvirtuallabs.calorimetry.cacl2.CalorimetryCalciumChloride;
import com.seanlee.chemistryvirtuallabs.calorimetry.nh4no3.CalorimetryAmmoniumNitrate;

// activity for calorimetry lab where users select which compound to use
public class CalorimetryChooseCompound extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorimetry_choose_compound);

        // button used to select cacl2 option
        Button cacl2Button = findViewById(R.id.cacl2button);

        // if cacl2Button is clicked
        cacl2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // go to CalorimetryCalciumChloride activity
                openCalorimetryCacl2();
            }
        });

        // button used to select nh4no3 option
        Button nh4no3Button = findViewById(R.id.nh4no3button);

        // if nh4no3Button is clicked
        nh4no3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // go to CalorimetryAmmoniumNitrate activity
                openCalorimetryNh4no3();
            }
        });
    }

    // changes activity to CalorimetryCalciumChloride
    public void openCalorimetryCacl2() {
        Intent intent = new Intent(this, CalorimetryCalciumChloride.class);
        startActivity(intent);
    }

    // changes activity to CalorimetryAmmoniumNitrate
    public void openCalorimetryNh4no3() {
        Intent intent = new Intent(this, CalorimetryAmmoniumNitrate.class);
        startActivity(intent);
    }
}
