package com.example.ratings;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Shift implements Comparable {

    private Date mShiftDate;
    private double mShiftDuration;



    public Shift(int day, int month, int year, double duration) {
        mShiftDate = new Date(year, month, day);

        mShiftDuration = duration;
    }
    public Shift(Date date, double duration) {
        mShiftDate = date;
        mShiftDuration = duration;
    }

    public Date getShiftDate() {
        return mShiftDate;
    }

    public double getShiftDuration() {
        return mShiftDuration;
    }

    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        return format.format(mShiftDate) + " " + mShiftDuration;
    }

    @Override
    public int compareTo(Object o) {
        Date d = ((Shift)o).getShiftDate();
        return this.getShiftDate().compareTo(d);
                //TODO yhh finish someday

    }
}
