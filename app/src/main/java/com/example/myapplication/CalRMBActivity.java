package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CalRMBActivity extends AppCompatActivity {
    TextView currency;
    EditText input;
    TextView result;
    float price_num;
    float input_num;
    String title;
    String price;
    private static final String TAG = "CalRMBActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cal_rmbactivity);

        Intent intent = getIntent();
        title = intent.getStringExtra("Currency");
        price = intent.getStringExtra("Price");
        Log.i(TAG, "onCreate: title = " +title);
        Log.i(TAG, "onCreate: price = " + price);
        price_num = Float.parseFloat(price);
        currency = findViewById(R.id.currency);
        currency.setText(title);

        input = findViewById(R.id.inputRMB);
    }

    public void click(View btn){

        try {
            input_num = Float.parseFloat(input.getText().toString());
            result = findViewById(R.id.result);
            float result_num = input_num * (100/price_num );
            result.setText(String.valueOf(result_num));
            Log.i(TAG, "click: result" + String.valueOf(result_num));
        }
        catch (NumberFormatException e) {
            Toast.makeText(this, "请输入正确金额", Toast.LENGTH_SHORT).show();;
        }

    }
}