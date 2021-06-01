package com.example.ratings;

import androidx.appcompat.app.AppCompatActivity;

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

//        View v = (View) findViewById(R.id.layout_rating_item);
//        v.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "Event Works", Toast.LENGTH_SHORT).show();
//            }
//        });


        ArrayList<Rating> ratings = generateDummyRatings();

        RatingAdapter ratingAdapter = new RatingAdapter(this, ratings);
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

        r2.addShift(new Shift(23,5,121, 2.5));
        r2.addShift(new Shift(24,5,121, 5));
        r2.addShift(new Shift(25,5,121, 4.5));
        r2.addShift(new Shift(26,5,121, 3));


        ar.add(r1);
        ar.add(r2);
        return ar;
    }
}