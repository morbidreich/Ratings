package com.example.ratings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.ratings.dataIO.dataIO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        //Ratings.setRatings(dataIO.loadRatings(this));

        //if (Ratings.ratingList.size() == 0);
            // open AddRatingAcvitity
        //else;
            // open MainActivity

        Intent i = new Intent(this, AddRatingActivity.class);
        startActivity(i);
        finish();
    }
}