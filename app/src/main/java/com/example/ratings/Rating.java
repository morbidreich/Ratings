package com.example.ratings;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class Rating {

    private String mRatingName;
    private ArrayList<Shift> mShifts;
    private Date mValidUntil;
    private String mValidUntilString;

    public Rating(String name, int year, int month, int day) {
        mRatingName = name;
        mValidUntil = new Date(year, month, day);
        mValidUntilString = calculateValidDate();
        mShifts = new ArrayList<>();
    }

    private String calculateValidDate() {
        return "";
    }

    @Override
    public String toString() {
        return mRatingName;
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
        Collections.sort(mShifts);

        //TODO add reference to method that updates rating valid date after each added shift
        // also check if there's no duplicate shifts, meaning same date, worktime doesnt matter here
    }

    public ArrayList<Shift> getShifts() {
        return mShifts;
    }
}
