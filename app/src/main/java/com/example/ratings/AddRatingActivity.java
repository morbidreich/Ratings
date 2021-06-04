package com.example.ratings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class AddRatingActivity extends AppCompatActivity {

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_rating);

        editText = (EditText) findViewById(R.id.edit_rating_name);
        Button button = (Button) findViewById(R.id.add_new_rating);

        button.setOnClickListener(mOnClickListener);

    }

    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            FileInputStream fis = null;


            try {
                fis = openFileInput(MainActivity.RATING_DATA);
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader br = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();
                String data;

                while ((data = br.readLine()) != null)
                    sb.append(data);

            }
            catch (FileNotFoundException e) {

                Ratings.addRating(editText.getText().toString());

                //Ratings.getRating(editText.getText().toString()).addShift(new Shift(12,3,121, 4.5));
                Log.v("dodałem rating ", editText.getText().toString());

                Intent i = new Intent(v.getContext(), MainActivity.class);
                startActivity(i);
                finish();

            }
            catch (IOException e) {
                Toast.makeText(AddRatingActivity.this, "IOException " + e, Toast.LENGTH_SHORT).show();
            }
        }
    };
}