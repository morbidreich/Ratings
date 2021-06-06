package com.example.ratings.dataIO;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.example.ratings.MainActivity;
import com.example.ratings.Rating;
import com.example.ratings.Ratings;
import com.example.ratings.WelcomeActivity;
import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DataIO {

    Gson gson = new Gson();

    public void readRatings(Activity activity, File[] list) {

        //this will hold number of String-Rating conversion problems gathered in catch block
        int errorCount = 0;

        //iterate through files list trying to convert each file into Rating object
        for (File f: list) {
            try {
                Rating r = gson.fromJson(readFile(f), Rating.class);
                Ratings.addRating(r);
            }
            //track number of problems
            catch (Exception e) {
                errorCount++;
            }
        }
        //inform user how many rating files were unable to load
        if (errorCount > 0) {
            String errMsg = "Problem reading " + errorCount + " out of " + list.length + " files";
            Toast.makeText(activity, errMsg , Toast.LENGTH_SHORT).show();
        }


    }

    /**
     * returns String containing file's content
     * @param f
     * @return
     */
    private String readFile(File f) {
        String out = "";
        try {
            Scanner sc = new Scanner(f);
            StringBuilder sb = new StringBuilder();
            while (sc.hasNextLine()) {
                sb.append(sc.nextLine());
            }
            out = sb.toString();
        } catch (FileNotFoundException e) {
            // TODO i dont think i need to implement that just because dataIO methods are
            // called only after previously verifying that files exist. Still will leave this
            //comment here
        }
        return out;
    }
}
