package com.example.ratings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ratings.dataIO.DataIO;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddShiftActivity extends AppCompatActivity {

    NumberPicker numDayPic;
    NumberPicker numMonthPic;
    NumberPicker numYearPic;
    NumberPicker numHoursPic;

    String ratingName;
    Calendar cal = Calendar.getInstance();
    Date shiftDate = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_shift);


        Bundle extras = getIntent().getExtras();
        if (extras != null)
            ratingName = extras.getString("ratingName");
        else ratingName = "";

        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText("Wybierz datę");
        MaterialDatePicker datePicker = builder.build();
        TextView datePreview = (TextView) findViewById(R.id.date_preview);
        datePicker.show(getSupportFragmentManager(), "");


        // grab all numberpickers

        numHoursPic = (NumberPicker) findViewById(R.id.hours_number_picker);

        //set default values / to be changed for datepicker

        String[] durations = {"0,5", "1", "1,5", "2", "2,5", "3", "3,5", "4", "4,5", "5", "5,5", "6", "6,5", "7", "7,5", "8", "8,5", "9", "9,5", "10"};


        numHoursPic.setMinValue(0);
        numHoursPic.setMaxValue(19);

        numHoursPic.setDisplayedValues(durations);

        numHoursPic.setValue(7);


        Button addButton = findViewById(R.id.button_add);
        addButton.setOnClickListener(mClickListener);

        Button showDatePickerButton = findViewById(R.id.button_show_date_picker);
        showDatePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker.show(getSupportFragmentManager(), "");
            }
        });

        //when user selects date then initialize shiftDate to create Shift item later on
        //also display selected date on datePreview TextView
        datePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object object) {
                Long x = (Long) object;
                shiftDate = new Date(x);
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                datePreview.setText(sdf.format(shiftDate));

            }
        });
        //if user press cancel in DatePicker then finish AddShiftActivity
        datePicker.addOnNegativeButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }

        });

        //finish activity when user clicks cancel button
        MaterialButton mbCancel = (MaterialButton) findViewById(R.id.cancel_button2);
        mbCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    //click listener for addButton
    View.OnClickListener mClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {

            //check if user selected date with DatePicker
            if (shiftDate == null) {
                Toast.makeText(AddShiftActivity.this, "Wybierz datę!", Toast.LENGTH_SHORT).show();
                return;
            }

            //create candidate to add with date selected in DatePicker and duration calculated
            //based on NumberPicker selected value index.

            Shift candidate = new Shift(shiftDate, (numHoursPic.getValue() * 0.5) + 0.5);

            boolean duplicate_found = false;

            //check if candidate matches already saved shifts
            for (Shift sh : Ratings.getRating(ratingName).getShifts()) {
                //if duplicate found change boolean to true
                if (sh.compareTo(candidate) == 0) {
                    duplicate_found = true;
                }
            }

            //if duplicate found then no go, and notify user
            if (duplicate_found) {
                Toast.makeText(AddShiftActivity.this, "Już dodałeś dyżur tego dnia!", Toast.LENGTH_SHORT).show();
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