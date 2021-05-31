package com.example.ratings;

import android.app.Application;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Rating implements Serializable {

    private String mRatingName;



    private ArrayList<Shift> mShifts;
    private Date mValidUntil;

    public Rating(String name, int year, int month, int day) {
        mRatingName = name;
        mValidUntil = new Date(year, month, day);
        mShifts = new ArrayList<>();

    }

    public String getRatingName() {
        return mRatingName;
    }

    public String getValidUntil() {

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");

        return format.format(mValidUntil);

    }

    public void addShift(Shift sh) {

        mShifts.add(sh);

        //TODO add reference to method that updates rating valid date after each added shift
    }

    public ArrayList<Shift> getShifts() {
        return mShifts;
    }
}
