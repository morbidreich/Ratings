package com.example.ratings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
        File[] list = myFiles.listFiles();



            Intent i = new Intent(this, AddRatingActivity.class);
            startActivity(i);
            finish();
    }
}