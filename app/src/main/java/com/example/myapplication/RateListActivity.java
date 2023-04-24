package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.List;

public class RateListActivity extends ListActivity {
    Handler handler;
    private static final String TAG = "RateListActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: run......");
//        List<String> mylist = new ArrayList<String>(100);
////        for(int i = 1;i<100;i++){
////            mylist.add("item" + i);
////        }
//        //Adapter
//        //String[] list_data = {"one","two","three","four"};
//        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,mylist);
//        setListAdapter(adapter);
        handler = new Handler(Looper.myLooper()) {
            public void handleMessage(@NonNull Message msg) {
                if (msg.what == 7) {
                    ArrayList<String> list2 = (ArrayList<String>) msg.obj;
                    Log.i(TAG, "handleMessage: 运行结果：" + list2);
                    ListAdapter adapter = new ArrayAdapter<String>(
                            RateListActivity.this,
                            android.R.layout.simple_list_item_1,
                            list2);
                    setListAdapter(adapter);
                }
                super.handleMessage(msg);
            }
        };
        //启动线程
        Thread thread = new Thread(new MyTask(handler));
        thread.start();

    }
}