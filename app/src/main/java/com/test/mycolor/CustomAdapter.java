package com.test.mycolor;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    Context context;
    ArrayList<ColorModel> array;

    public CustomAdapter(Context context, ArrayList<ColorModel> array) {
        this.context = context;
        this.array = array;
    }


    @Override
    public int getCount() {

        return array.size();
    }

    @Override
    public Object getItem(int position) {

        return array.get(position);
    }

    @Override
    public long getItemId(int position) {


        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater _inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (v == null) {
            v = _inflater.inflate(R.layout.custom_adapter, null);
        }

        ConstraintLayout layout = v.findViewById(R.id.custom_row);
        layout.setBackgroundColor(array.get(position).getColorCode());



        return v;

    }
}
