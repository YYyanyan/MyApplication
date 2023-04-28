package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BMI extends AppCompatActivity {

        EditText height;
        EditText weight;
        Button btn;
        TextView bmiout;
        TextView stateout;
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_bmi);
            height = findViewById(R.id.height);
            weight = findViewById(R.id.weight);
            btn = findViewById(R.id.btn);
        }
        public void btn(View view) {
//        Log.i(TAG, "onClick: ");
            Float h = Float.parseFloat(height.getText().toString());
            Float w = Float.parseFloat(weight.getText().toString());
            Float bmi = w / (h * h);
            bmiout = findViewById(R.id.bmi);
            bmiout.setText(String.format("%.2f", bmi).toString());
            stateout = findViewById(R.id.state);
            if (bmi < 18.5) {
                stateout.setText("偏瘦，建议适当增重");
            } else if (bmi < 24) {
                stateout.setText("正常");
            } else if (bmi < 28) {
                stateout.setText("偏胖，有轻度疾病风险，建议适当减重");
            } else if (bmi < 30) {
                stateout.setText("肥胖，有疾病风险，建议控制饮食，适当锻炼");
            } else {
                stateout.setText("重度肥胖，有严重疾病风险，建议遵循医嘱，控制饮食，适当锻炼");
            }
        }


    }
