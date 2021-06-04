package com.example.ratings.dataIO;

import android.content.Context;

import com.example.ratings.MainActivity;
import com.example.ratings.Rating;
import com.example.ratings.Ratings;
import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.*;
import java.util.ArrayList;

public class dataIO {

    public static ArrayList<Rating> loadRatings(Context context) {

        ArrayList<Rating> list = new ArrayList<>();

        FileInputStream fis = null;
        try {
            fis = context.openFileInput(MainActivity.RATING_DATA);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String input;

            while ((input = br.readLine()) != null)
                sb.append(input);

            Gson gson = new Gson();


        }
        catch (FileNotFoundException e) {

        }
        catch (IOException e) {

        }
        if (fis != null) {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



        return list;
    }
}
