package com.example.ratings.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ratings.R;
import com.example.ratings.Ratings;
import com.example.ratings.Shift;
import com.example.ratings.dataIO.DataIO;

import java.util.ArrayList;
import java.util.List;

public class ShiftAdapter extends ArrayAdapter<Shift> {

    ArrayList<Shift> mShifts;
    String ratingName = "";

    public ShiftAdapter(@NonNull Context context, @NonNull ArrayList<Shift> objects, String ratingName) {
        super(context, 0, objects);
        mShifts = objects;
        this.ratingName = ratingName;

    }

    @Nullable
    @Override
    public Shift getItem(int position) {
        return super.getItem(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listViewItem = convertView;
        if (listViewItem == null)
            listViewItem = LayoutInflater.from(getContext()).inflate(R.layout.shift_item, parent, false);

        final Shift currentShift = getItem(position);


        TextView textViewShiftDate = (TextView) listViewItem.findViewById(R.id.shift_text);
        if (currentShift != null)
            textViewShiftDate.setText(currentShift.toString());
        else
            textViewShiftDate.setText("null");



        ImageView imageViewDelete = (ImageView) listViewItem.findViewById(R.id.ic_delete_shift);
        imageViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder adb = new AlertDialog.Builder(v.getContext());
                adb.setTitle("Uwaga");
                final int posToRemove = position;
                adb.setMessage("Czy na pewno usunąć dyżur " + currentShift.getShiftDate().toString() + "?");
                adb.setNegativeButton("Nie", null);
                adb.setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(v.getContext(), ratingName, Toast.LENGTH_SHORT).show();
                        Ratings.getRating(ratingName).deleteShift(currentShift);
                        DataIO.saveAllRatings(getContext());
                        ShiftAdapter.super.notifyDataSetChanged();
                    }
                });
                adb.show();
            }
        });


        return listViewItem;
    }
}
