package com.example.ratings;


import android.util.Log;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

public class Rating {

    private String mRatingName;
    private ArrayList<Shift> mShifts;
    private Date mValidUntil;

    public Rating(String name, int year, int month, int day) {
        mRatingName = name;
        mValidUntil = new Date(year, month, day);

        mShifts = new ArrayList<>();
        //
    }

    public Rating(String name) {
        mRatingName = name;
        mShifts = new ArrayList<>();
        //TODO
    }



    // rating is valid for 90 days after 12hrs of work has been recorded
    // if less than 12 hrs of work is recorded i return last work day date
    // as ratingValidUntil
    private Date ratingValidTo() {
        double totalWorkTime = 0;

        for (int i = mShifts.size() - 1; i >= 0; i--) {
            totalWorkTime += mShifts.get(i).getShiftDuration();
            if (totalWorkTime >= 12) {
                Calendar c = Calendar.getInstance();
                c.setTime(mShifts.get(i).getShiftDate());
                c.add(Calendar.DATE, 90);
                return c.getTime();
            }
        }
        return mShifts.get(mShifts.size() -1).getShiftDate();
    }

    @Override
    public String toString() {
        return mRatingName;
    }

    public String getRatingName() {
        return mRatingName;
    }

    public String getValidUntil() {
        if (mValidUntil == null)
            return "nieważne!";
        else {
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
            return format.format(mValidUntil);
        }
    }

    public void addShift(Shift sh) {

        mShifts.add(sh);
        Collections.sort(mShifts);
        mValidUntil = ratingValidTo();


        Log.v("ValidUntil", mValidUntil.toString());

        //TODO add reference to method that updates rating valid date after each added shift
        // also check if there's no duplicate shifts, meaning same date, worktime doesnt matter here
    }

    public ArrayList<Shift> getShifts() {
        return mShifts;
    }
}
