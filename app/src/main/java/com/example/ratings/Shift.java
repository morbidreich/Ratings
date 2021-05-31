package com.example.ratings;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class Shift {

    private Date mShiftDate;
    private double mShiftDuration;



    public Shift(int day, int month, int year, double duration) {
        mShiftDate = new Date(year, month, day);
        mShiftDuration = duration;
    }

    public Date getShiftDate() {
        return mShiftDate;
    }

    public double getShiftDuration() {
        return mShiftDuration;
    }
}
