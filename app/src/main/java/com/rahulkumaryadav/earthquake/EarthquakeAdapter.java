package com.rahulkumaryadav.earthquake;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.List;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
    public EarthquakeAdapter(@NonNull Context context, @NonNull List<Earthquake> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView==null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_list_item,parent,false);
        }

        Earthquake currentEarthquake = getItem(position);

        TextView magnitude = listItemView.findViewById(R.id.magnitude);
        TextView location = listItemView.findViewById(R.id.location);
        TextView date = listItemView.findViewById(R.id.date);
        TextView primaryLocation =listItemView.findViewById(R.id.primary_location);

        String fullLocation = currentEarthquake.getLocation();
        if (fullLocation.indexOf("of")!=-1){
            location.setText(fullLocation.substring(0,fullLocation.indexOf("of")+2));
            primaryLocation.setText(fullLocation.substring(fullLocation.indexOf("of")+3));
        }
        else {
            location.setText("Near the");
            primaryLocation.setText(fullLocation);
        }
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitude.getBackground();

        magnitudeCircle.setColor(getMagnitudeColor(currentEarthquake.getMagnitude()));
        magnitude.setText(currentEarthquake.getMagnitude());
        date.setText(currentEarthquake.getDate());

        return listItemView;
    }

    private int getMagnitudeColor(String magnitude){
        int mag=(int)(Float.parseFloat(magnitude));
        int color;
        switch (mag){
            case 0:
            case 1:
                color = R.color.magnitude1;
                break;
            case 2:
                color = R.color.magnitude2;
                break;
           case 3:
                color = R.color.magnitude3;
                break;
           case 4:
                color = R.color.magnitude4;
                break;
           case 5:
                color = R.color.magnitude5;
                break;
           case 6:
                color = R.color.magnitude6;
                break;
           case 7:
                color = R.color.magnitude7;
                break;
           case 8:
                color = R.color.magnitude8;
                break;
           case 9:
                color = R.color.magnitude9;
                break;
            default:
                color = R.color.magnitude10plus;
                break;

        }
        return ContextCompat.getColor(getContext(),color);
    }
}
