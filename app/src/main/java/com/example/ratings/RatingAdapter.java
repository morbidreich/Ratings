package com.example.ratings;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RatingAdapter extends ArrayAdapter<Rating> {


    public RatingAdapter(@NonNull Context context, @NonNull ArrayList<Rating> objects) {
        super(context, 0, objects);
    }

    @Nullable
    @Override
    public Rating getItem(int position) {
        return super.getItem(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.rating_item, parent, false);
        }

        Rating currentRating = getItem(position);

        /* bind view's items to data */
        TextView ratingNameTextView = (TextView)listItemView.findViewById(R.id.rating_name);
        ratingNameTextView.setText(currentRating.getRatingName());

        TextView ratingValidUntilTextView = (TextView)listItemView.findViewById(R.id.date_valid_to);
        ratingValidUntilTextView.setText(currentRating.getValidUntil());

        /* event that opens activity displaying all recorded shifts for this rating */
        ImageView shiftListImageView = (ImageView) listItemView.findViewById(R.id.image_shift_list);
        shiftListImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), ShiftListActivity.class);
                v.getContext().startActivity(i);
            }
        });

        /* event that opens activity for adding shifts for selected rating */
        ImageView addShiftImageView = (ImageView) listItemView.findViewById(R.id.image_add_shift);
        addShiftImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), AddShiftActivity.class);
                v.getContext().startActivity(i);
            }
        });

        return listItemView;
    }
}
