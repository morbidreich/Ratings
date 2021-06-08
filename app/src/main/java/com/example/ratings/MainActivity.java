package com.example.ratings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ratings.adapters.RatingAdapter;
import com.google.gson.Gson;

import java.nio.file.Files;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public final static String RATING_DATA = "ratings_data.txt";
    private static boolean isInitialized = false;
    private ListView listView;
    RatingAdapter ratingAdapter;
    Button addRatingButton;


    @Override
    protected void onResume() {
        super.onResume();

        //update ListView containing Rating elements
        ratingAdapter.notifyDataSetChanged();
    }

    public RatingAdapter getRatingAdapter() {
        return ratingAdapter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addRatingButton = (Button) findViewById(R.id.add_rating);

        ratingAdapter = new RatingAdapter(this, Ratings.getRatingList());
        listView = (ListView)findViewById(R.id.ratings_list);
        listView.setAdapter(ratingAdapter);

        addRatingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), AddRatingActivity.class);
                startActivity(i);
            }
        });







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