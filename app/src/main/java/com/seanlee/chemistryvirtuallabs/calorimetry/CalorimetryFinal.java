package com.seanlee.chemistryvirtuallabs.calorimetry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.seanlee.chemistryvirtuallabs.MainActivity;
import com.seanlee.chemistryvirtuallabs.R;

// the final concluding activity of calorimetry lab
public class CalorimetryFinal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorimetry_final);

        // get the score value passed from the previous activity
        Bundle b = getIntent().getExtras();
        final int score = b.getInt("score");

        // various images of the user's score - all initially set to invisible
        ImageView score04 = findViewById(R.id.score04);
        ImageView score14 = findViewById(R.id.score14);
        ImageView score24 = findViewById(R.id.score24);
        ImageView score34 = findViewById(R.id.score34);
        ImageView score44 = findViewById(R.id.score44);

        // make one of the scores visible depending on score
        if (score == 0) {
            score04.setVisibility(View.VISIBLE);
        } else if (score == 1) {
            score14.setVisibility(View.VISIBLE);
        } else if (score == 2) {
            score24.setVisibility(View.VISIBLE);
        } else if (score == 3) {
            score34.setVisibility(View.VISIBLE);
        } else if (score == 4) {
            score44.setVisibility(View.VISIBLE);
        }

        // button used to go to MainActivity
        Button home = findViewById(R.id.home);

        // if home is clicked
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // go to MainActivity
                openMain();
            }
        });
    }

    // if back button is clicked
    @Override
    public void onBackPressed() {
        // do nothing - prevents users from repeating questions multiple times
    }

    // changes activity to MainActivity
    public void openMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
