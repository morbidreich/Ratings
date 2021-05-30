package com.example.ratings;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Rating {

    private String mRatingName;
    private ArrayList<Shift> mShifts;
    private Date mValidUntil;

    public Rating(String name, int year, int month, int day) {
        mRatingName = name;
        mValidUntil = new Date(year, month, day);

    }

    public String getRatingName() {
        return mRatingName;
    }

    public String getValidUntil() {

        return mValidUntil.getDay() + " " + mValidUntil.getMonth() + " " + mValidUntil.getYear();

    }
}
