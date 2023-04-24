package com.example.myapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyAdapter extends ArrayAdapter {
    private static final String TAG = "MyAdapter";
    public MyAdapter(@NonNull Context context, int resource, @NonNull ArrayList<HashMap<String, String>> list) {
        super(context, resource, list);

    }
        public View getView ( int position, View convertView, ViewGroup parent){
            View itemView = convertView;
            if (itemView == null) {
                itemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,
                        parent, false);
            }

                Map<String, String> map = (Map<String, String>) getItem(position);
                TextView title = itemView.findViewById(R.id.itemTitle);
                TextView price = itemView.findViewById(R.id.price);

                title.setText(map.get("Currency"));
                price.setText(map.get("Price"));
                Log.i(TAG, "getView: title = " + title);
                Log.i(TAG, "getView: price = " + price);


                return itemView;
        }
    }

