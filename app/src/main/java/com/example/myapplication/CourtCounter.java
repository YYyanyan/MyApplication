package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class CourtCounter extends AppCompatActivity {
    TextView scoreA;
    TextView scoreB;
    private static final String TAG = "CourtCounter";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_court_counter);
        scoreA = findViewById(R.id.scoreA);
        scoreB = findViewById(R.id.scoreB);

    }
    public void click(View btn){
        Integer scoreA_num = Integer.parseInt(scoreA.getText().toString());
        Integer scoreB_num = Integer.parseInt(scoreB.getText().toString());

        if(btn.getId() == R.id.threeA ){
            scoreA_num += 3;
            scoreA.setText(String.valueOf(scoreA_num));
        }else if(btn.getId() == R.id.twoA ){
            scoreA_num += 2;
            scoreA.setText(String.valueOf(scoreA_num));
        }else if(btn.getId() == R.id.oneA ){
            scoreA_num += 1;
            scoreA.setText(String.valueOf(scoreA_num));
        }else if(btn.getId() == R.id.threeB ){
            scoreB_num += 3;
            scoreB.setText(String.valueOf(scoreB_num));
        }else if(btn.getId() == R.id.twoB ){
            scoreB_num += 2;
            scoreB.setText(String.valueOf(scoreB_num));
        }else if(btn.getId() == R.id.oneB ){
            scoreB_num += 3;
            scoreB.setText(String.valueOf(scoreB_num));
        }


    }
    public void reset(View reset){
        scoreA.setText("0");
        scoreB.setText("0");
        Log.i(TAG, "reset: " + "0");

    }
}