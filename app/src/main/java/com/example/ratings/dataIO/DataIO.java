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
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

public class DataIO {

    Gson gson = new Gson();


    public static void saveAllRatings(Context context) {
        Gson gson = new Gson();
        String out = "";

        //that's a total primitive mess but for now have to do
        //first i delete all files, then create new ones
        //based on existing Rating items in Ratings


        File dir = new File(context.getFilesDir().toString());
        File[] fileList = dir.listFiles();
        for (File f:fileList)
            f.delete();

        //now when all files are deleted we can create new ones

        for (Rating r : Ratings.getRatingList()) {
            try {
                OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput(r.getRatingName(), Context.MODE_PRIVATE));
                out = gson.toJson(r, Rating.class);
                osw.write(out);
                osw.close();
            }
            catch (IOException e) {
                Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    public static void loadAllRatings(Context context) {
        File myFiles = new File(context.getFilesDir().toString());
        File[] files = myFiles.listFiles();
        String out = "";
        ArrayList<Rating> ratingList = new ArrayList<>();
        Gson gson = new Gson();

        for (File f: files) {
            try {
                Scanner sc = new Scanner(f);
                StringBuilder sb = new StringBuilder();
                while(sc.hasNextLine()) {
                    sb.append(sc.nextLine());
                }
                out = sb.toString();
                ratingList.add(gson.fromJson(out, Rating.class));
            }
            catch (FileNotFoundException e) {}
            Ratings.setRatings(ratingList);
        }
    }

    public void readRatings(Activity activity, File[] list) {

        //this will hold number of String-Rating conversion problems gathered in catch block
        int errorCount = 0;

        //iterate through files list trying to convert each file into Rating object
        for (File f : list) {
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
            Toast.makeText(activity, errMsg, Toast.LENGTH_SHORT).show();
        }


    }

    /**
     * returns String containing file's content
     *
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
