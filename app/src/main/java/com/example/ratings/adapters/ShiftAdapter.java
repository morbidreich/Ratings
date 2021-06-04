package com.example.ratings.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ratings.R;
import com.example.ratings.Shift;

import java.util.ArrayList;
import java.util.List;

public class ShiftAdapter extends ArrayAdapter<Shift> {

    List<Shift> mShifts;

    public ShiftAdapter(@NonNull Context context, @NonNull ArrayList<Shift> objects) {
        super(context, 0, objects);

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

        return listViewItem;
    }
}
