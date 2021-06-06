package com.example.ratings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ratings.dataIO.DataIO;

import java.util.ArrayList;
import java.util.Calendar;

public class AddShiftActivity extends AppCompatActivity {

    NumberPicker numDayPic;
    NumberPicker numMonthPic;
    NumberPicker numYearPic;
    NumberPicker numHoursPic;

    String ratingName;
    Calendar cal = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shift);


        Bundle extras = getIntent().getExtras();
        if (extras != null)
            ratingName = extras.getString("ratingName");
        else ratingName = "";


        // grab all numberpickers
        numDayPic = (NumberPicker) findViewById(R.id.day_number_picker);
        numMonthPic = (NumberPicker) findViewById(R.id.month_number_picker);
        numYearPic = (NumberPicker) findViewById(R.id.year_number_picker);
        numHoursPic = (NumberPicker) findViewById(R.id.hours_number_picker);

        //set default values / to be changed for datepicker
        numDayPic.setMaxValue(31);
        numDayPic.setValue(cal.getTime().getDay());
        numDayPic.setMinValue(1);

        numMonthPic.setMaxValue(12);
        numMonthPic.setValue(cal.getTime().getMonth()+1);
        numMonthPic.setMinValue(1);

        numYearPic.setMaxValue(2028);
        numYearPic.setValue(cal.getTime().getYear()+1900);
        numYearPic.setMinValue(2018);

        numHoursPic.setMaxValue(10);
        numHoursPic.setValue(4);
        numHoursPic.setMinValue(1);




        Button addButton = findViewById(R.id.button_add);
        addButton.setOnClickListener(mClickListener);

        Button backButton = findViewById(R.id.button_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    View.OnClickListener mClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            //create candidate to add
            Shift candidate = new Shift(numDayPic.getValue(), numMonthPic.getValue()-1, numYearPic.getValue() - 1900, numHoursPic.getValue());

            boolean duplicate_found = false;


            //check if candidate matches already saved shifts
            for(Shift sh: Ratings.getRating(ratingName).getShifts()) {
                //if duplicate found change variable value
                if (sh.compareTo(candidate) == 0) {
                   duplicate_found = true;
                }
            }

            //if duplicate found then no go, and notify user
            if (duplicate_found) {
                Toast.makeText(AddShiftActivity.this, "Już dodałeś dyżur tego dnia!", Toast.LENGTH_SHORT).show();
                //finish();
            }
            //if no duplicate found then add shift and notify user
            else {
                Toast.makeText(AddShiftActivity.this, "Dodano " + candidate.toString(), Toast.LENGTH_SHORT).show();
                Ratings.getRating(ratingName).addShift(candidate);
                DataIO.saveAllRatings(AddShiftActivity.this);
//
                finish();
            }
        }
    };
}