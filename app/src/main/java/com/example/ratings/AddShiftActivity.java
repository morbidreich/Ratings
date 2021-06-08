package com.example.ratings;

import androidx.appcompat.app.AppCompatActivity;

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

        datePicker.addOnPositiveButtonClickListener(new MaterialPickerOnPositiveButtonClickListener() {
            @Override
            public void onPositiveButtonClick(Object object) {
                Long x = (Long) object;
                shiftDate = new Date(x);
                //Toast.makeText(AddShiftActivity.this, shiftDate.toString(), Toast.LENGTH_SHORT).show();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                datePreview.setText(sdf.format(shiftDate));

            }
        });

        MaterialButton mbCancel = (MaterialButton) findViewById(R.id.cancel_button2);
        mbCancel.setOnClickListener(new View.OnClickListener() {
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
            if (shiftDate == null) {
                Toast.makeText(AddShiftActivity.this, "Wybierz datę!", Toast.LENGTH_SHORT).show();
                return;

            }

            Shift candidatex = new Shift(shiftDate, (numHoursPic.getValue() * 0.5)+0.5);

            //Shift candidate = new Shift(numDayPic.getValue(), numMonthPic.getValue()-1, numYearPic.getValue() - 1900, numHoursPic.getValue());

            boolean duplicate_found = false;


            //check if candidate matches already saved shifts
            for (Shift sh : Ratings.getRating(ratingName).getShifts()) {
                //if duplicate found change variable value
                if (sh.compareTo(candidatex) == 0) {
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
                Toast.makeText(AddShiftActivity.this, "Dodano " + candidatex.toString(), Toast.LENGTH_SHORT).show();
                Ratings.getRating(ratingName).addShift(candidatex);
                DataIO.saveAllRatings(AddShiftActivity.this);
//
                finish();
            }
        }
    };
}