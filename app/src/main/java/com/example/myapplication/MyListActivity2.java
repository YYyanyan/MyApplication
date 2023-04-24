package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class MyListActivity2 extends ListActivity implements AdapterView.OnItemClickListener {
    Handler handler;
    private ArrayList listItems;
    private SimpleAdapter listItemAdapter;
    private static final String TAG = "MyListActivity2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate: run......");

        super.onCreate(savedInstanceState);
        handler = new Handler(Looper.myLooper()) {
            public void handleMessage(@NonNull Message msg) {
                if (msg.what == 7) {
//                    Log.i(TAG, "handleMessage: 运行结果：" + list2);
                    listItems = new ArrayList<HashMap<String,String>>();
                    listItems = (ArrayList) msg.obj;
//                    listItemAdapter = new SimpleAdapter(
//                            MyListActivity2.this,
//                            listItems,
//                            R.layout.list_item,
//                            new String[] {"Currency","Price"},
//                            new int[] {R.id.itemTitle,R.id.price});
                    MyAdapter adapter = new MyAdapter(MyListActivity2.this,R.layout.list_item,listItems);
                    setListAdapter(adapter);
                    getListView().setOnItemClickListener(MyListActivity2.this);
                }
                super.handleMessage(msg);

            }
        };
        //启动线程
        Thread thread = new Thread(new MyTask(handler));
        thread.start();

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        HashMap<String,String> map  = (HashMap<String, String>) getListView().getItemAtPosition(i);
        String title = map.get("Currency");
        String price = map.get("Price");
        Log.i(TAG, "onItemClick: Title = " + title);
        Log.i(TAG, "onItemClick: Pirce = " + price);

    }
}