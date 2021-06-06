package com.example.ratings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.ratings.dataIO.DataIO;

import java.io.File;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        //check for saved Rating files in apps internal storage

        File myFiles = new File(this.getFilesDir().toString());
        File[] files = myFiles.listFiles();
        
        if (files.length == 0) {
            Intent i = new Intent(this, AddRatingActivity.class);
            startActivity(i);
            finish();
        }
        else {
            DataIO.loadAllRatings(this);
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            finish();
        }
    }
}