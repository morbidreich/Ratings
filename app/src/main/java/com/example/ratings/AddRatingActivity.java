package com.example.ratings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ratings.dataIO.DataIO;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class AddRatingActivity extends AppCompatActivity {

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rating);

        // view for user to input desired rating name
        editText = (TextInputEditText) findViewById(R.id.edit_rating_name);

        // accept button
        Button button = (Button) findViewById(R.id.add_new_rating);

        //cancel button
        MaterialButton cancelBUtton = (MaterialButton) findViewById(R.id.cancel_button);

        //textview for description for user
        TextView tvDescription = (TextView) findViewById(R.id.add_rating_description);

        //modify TextView containing description based on check if user adds his first rating,
        //or next. Description differ a bit
        if (Ratings.ratingList.size()==0)
            tvDescription.setText("Nie dodałeś jeszcze żadnych uprawnień.\nPodaj nazwę w polu poniżej by to zrobić");
        else
            tvDescription.setText("Podaj nazwę uprawnienia w polu poniżej");





        //hook event listener for accept button
        button.setOnClickListener(mOnClickListener);

        //handle cancel button click
        cancelBUtton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //listener for accept click
    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String newRating = editText.getText().toString();
            //try to add rating, exception will be thrown if rating with same name already exist
            try {
                //wanna make sure that rating name is not empty, no big deal but just looks bad later in list :)
                if (!newRating.isEmpty()) {
                    //add Rating object
                    Ratings.addRating(newRating);
                    //update data files
                    DataIO.saveAllRatings(v.getContext());
                    //finish this activity and launch MainActivity
                    Intent i = new Intent(v.getContext(), MainActivity.class);
                    startActivity(i);
                    finish();
                }
                else
                    Toast.makeText(AddRatingActivity.this, "Musisz podać nazwę!", Toast.LENGTH_SHORT).show();
            }
            catch (Exception e) {
                Toast.makeText(AddRatingActivity.this, "Ta nazwa jest zajęta", Toast.LENGTH_SHORT).show();
            }
        }
    };


}