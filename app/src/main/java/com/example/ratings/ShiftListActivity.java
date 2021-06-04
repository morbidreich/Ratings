package com.example.ratings;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ratings.adapters.ShiftAdapter;

import java.util.ArrayList;

public class ShiftListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shift_list);

        String ratingName = "";

        Bundle extras = getIntent().getExtras();
        if (extras != null)
            ratingName = extras.getString("ratingName");

        Toast.makeText(this, ratingName, Toast.LENGTH_SHORT).show();


        ArrayList<Shift> shiftList = Ratings.getRating(ratingName).getShifts();

        //set adapter for listview
        ShiftAdapter shiftAdapter = new ShiftAdapter(this, shiftList);
        ListView listView = (ListView) findViewById(R.id.shift_list);
        listView.setAdapter(shiftAdapter);


        // handle back button onClickListener - back to main activity
        Button backButton = (Button) findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}