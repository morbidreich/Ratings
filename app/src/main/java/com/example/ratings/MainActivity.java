package com.example.ratings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO
        /* read ratings from file, use gson to parse object/string.

        if no files, show screen allowing creating of ratings

        if files ok then display list of ratings


         */

        Ratings.setRatings(generateDummyRatings());

        RatingAdapter ratingAdapter = new RatingAdapter(this, Ratings.getRatingList());
        ListView listView = (ListView)findViewById(R.id.ratings_list);
        listView.setAdapter(ratingAdapter);


    }

    ArrayList<Rating> generateDummyRatings () {

        ArrayList<Rating> ar = new ArrayList<>();
        // pierwszy
        Rating r1 = new Rating("EPLB", 121,10,20);

        r1.addShift(new Shift(23,5,121, 2.5));
        r1.addShift(new Shift(24,5,121, 5));
        r1.addShift(new Shift(25,5,121, 4.5));
        r1.addShift(new Shift(26,5,121, 3));


        // drugi
        Rating r2 = new Rating("EPSY", 121,8,15);

        r2.addShift(new Shift(23,2,121, 4));
        r2.addShift(new Shift(4,2,121, 2));
        r2.addShift(new Shift(15,2,121, 5.5));
        r2.addShift(new Shift(26,2,121, 2));
        r2.addShift(new Shift(11,5,121,4));


        ar.add(r1);
        ar.add(r2);
        return ar;
    }
}