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

        editText = (EditText) findViewById(R.id.edit_rating_name);
        TextView out_display = (TextView) findViewById(R.id.output_monitor);
        Button button = (Button) findViewById(R.id.add_new_rating);

        button.setOnClickListener(mOnClickListener);

    }

    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String newRating = editText.getText().toString();

            //try to add rating, exception will be thrown if rating with same name already exist
            try {
                Ratings.addRating(newRating);
                DataIO.saveAllRatings(v.getContext());

                Intent i = new Intent(v.getContext(), MainActivity.class);
                startActivity(i);
                finish();
            }
            catch (Exception e) {
                Toast.makeText(AddRatingActivity.this, "Nie mogę dodać drugiego uprawnienia o tej samej nazwie", Toast.LENGTH_SHORT).show();
            }


        }
    };
}