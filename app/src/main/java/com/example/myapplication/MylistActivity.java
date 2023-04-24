package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class MylistActivity extends ListActivity {
    private ArrayList listItems;
    private SimpleAdapter listItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initListView();
        setListAdapter(listItemAdapter);

    }
    private void initListView(){
        listItems = new ArrayList<HashMap<String,String>>();
        for (int i=0; i<10;i++){
            HashMap<String,String> map = new HashMap<String,String>();
            map.put("ItemTitle","Rate：" + i);
            map.put("Price","detail：" + i);
            listItems.add(map);
        }
        listItemAdapter = new SimpleAdapter(this,
                listItems,
                R.layout.list_item,
                new String[] {"ItemTitle","Price"},
                new int[] {R.id.itemTitle,R.id.price});

    }
}