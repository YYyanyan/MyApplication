package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;

public class NetActivity extends AppCompatActivity implements Runnable{
    Handler handler;
    private static final String TAG = "NetActivity";
    URL url = null;
    TextView text;
    String resStr;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net);
        Log.i(TAG, "onCreate: test");
        handler = new Handler(Looper.myLooper()) {
            public void handleMessage(@NonNull Message msg) {
                if (msg.what == 7) {
                    String str = (String)msg.obj;
                    //收到数据
                    Log.i(TAG, "handleMessage: 收到数据" + str);
                    text = findViewById(R.id.result);
                    text.setText(str);
//                    Toast.makeText(NetActivity.this, str, Toast.LENGTH_SHORT).show();
                }
                super.handleMessage(msg);
            }
        };
    }
    public void onClick(View btn){
        Thread t  = new Thread(this);
        Log.i(TAG, "onCreate: 启动子线程");
        t.start();
    }
    @Override
    public void run() {
        try {
//            url = new URL("https://chl.cn/?jinri");
//            HttpURLConnection http = (HttpURLConnection) url.openConnection();
//            InputStream in = http.getInputStream();
//            String html = inputStream2String(in);
//            Log.i(TAG, "run: html = "+ html);
//            TextView htmlText = findViewById(R.id.html);
//            htmlText.setText(html);
            Document doc =Jsoup.connect("https://chl.cn/?jinri").get();
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
                Log.i(TAG, "run: 币种：" + td1.text() + "价格：" + td2.text());
//                Log.i(TAG, "run: 币种" + td1.html() + "价格2" + td2.html());

                resStr += "币种：" + td1.text() + "==>" + td2.text() + "\n";

            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Log.i(TAG, "run: 线程已运行");
        //发送数据给主线程
        Message msg = handler.obtainMessage();
        msg.what = 7;
        msg.obj = resStr;
        handler.sendMessage(msg);
    }
    private String inputStream2String(InputStream inputStream) throws IOException {
            final int bufferSize = 1024;
            final  char[] buffer = new char[bufferSize];
            final StringBuilder out = new StringBuilder();
            Reader in = new InputStreamReader(inputStream,"utf-8");
            while (true){
                int rsz = in.read(buffer,0,buffer.length);
                if(rsz<0)
                    break;
                out.append(buffer,0,rsz);
            }
            return out.toString();
        }
    }