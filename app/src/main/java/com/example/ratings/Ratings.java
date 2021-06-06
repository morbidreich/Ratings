package com.example.ratings;


import android.util.Log;
import android.widget.Toast;
import java.util.ArrayList;

public class Ratings {

    static ArrayList<Rating> ratingList;


    /**
     * allows to initialize local static list with items, for now dummy generated,
     * later from local storage
     * @param list list holding @Rating items
     */
    public static void setRatings (ArrayList<Rating> list) {
        ratingList = list;
    }

    public static ArrayList<Rating> getRatingList() {
        return ratingList;
    }

    public static void addRating(String name) {
        if (ratingList == null)
            ratingList = new ArrayList<>();

        Rating r = new Rating(name);
        ratingList.add(r);
    }
    public static void addRating(Rating r) {
        ratingList.add(r);
    }

    /**
     * returns Rating with matching name param
     * @param name rating name
     * @return Rating
     */
    public static Rating getRating(String name) {

        for (Rating rat: ratingList) {
            if (name.equals(rat.toString()))
                return rat;
        }
               return null;
    }
}
