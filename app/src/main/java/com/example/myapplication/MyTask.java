package com.example.myapplication;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.DocumentType;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyTask implements Runnable{
    private Handler handler;
    public MyTask(Handler handler){this.handler = handler;};
    private static final String TAG = "MyTask";
    private HashMap<String,String> map;
    private ArrayList listItems;
    public void run() {
        Log.i(TAG, "run: 线程已运行");
        Bundle retBundle = new Bundle();
        List<String> retlist = new ArrayList<>();
        listItems = new ArrayList<HashMap<String,String>>();
        try {
//            url = new URL("https://chl.cn/?jinri");
//            HttpURLConnection http = (HttpURLConnection) url.openConnection();
//            InputStream in = http.getInputStream();
//            String html = inputStream2String(in);
//            Log.i(TAG, "run: html = "+ html);
//            TextView htmlText = findViewById(R.id.html);
//            htmlText.setText(html);
            Document doc = (Document) Jsoup.connect("https://chl.cn/?jinri").get();
            Log.i(TAG, "run: title=" + doc.title());
            Elements tables = doc.getElementsByTag("table");
            Element table1 = tables.first();
            Log.i(TAG, "run: table = " + table1);
            Elements rows = table1.getElementsByTag("tr");
            for(Element row : rows){
                //拆分单元格td
                Elements tds = row.getElementsByTag("td");
                Element td1 = tds.first();
                Element td2 = tds.get(4);
                String str1 = td1.text().trim();
                String str2 = td2.text().trim();
                Log.i(TAG, "run: 币种：" + td1.text() + "价格：" + td2.text());
                Log.i(TAG, "run: 币种2" + td1.html() + "价格2" + td2.html());

                //resStr += "币种：" + td1.text() + "==>" + td2.text() + "\n";
//                retlist.add(str1 + "=>" + str2);
                map = new HashMap<String,String>();
                map.put("Currency",str1);
                map.put("Price",str2);
                listItems.add(map);


            }
//            retlist.remove(0);
            listItems.remove(0);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Log.i(TAG, "run: 线程已运行");
        //发送数据给主线程
        Message msg = handler.obtainMessage();
        msg.what = 7;
        //msg.obj = retlist;
        msg.obj = listItems;
        handler.sendMessage(msg);
    }
    }

