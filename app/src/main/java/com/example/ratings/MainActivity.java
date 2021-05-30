package com.example.ratings;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Rating> ratings = generateDummyRatings();
        RatingAdapter ratingAdapter = new RatingAdapter(this, ratings);
        ListView listView = (ListView)findViewById(R.id.ratings_list);
        listView.setAdapter(ratingAdapter);
    }

    ArrayList<Rating> generateDummyRatings() {
        ArrayList<Rating> dummy = new ArrayList<>();

        dummy.add(new Rating("EPLB", 2021,9,30));
        dummy.add(new Rating("EPSY", 2021, 8, 14));
        dummy.add(new Rating("EPKK", 2021, 7, 8));

        return dummy;
    }
}