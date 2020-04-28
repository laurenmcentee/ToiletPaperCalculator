package com.example.toiletpapercalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    TextView textViewNumberOfDays;
    TextView textViewDays;
    TextView textViewInfo;
    Button   buttonBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textViewDays = findViewById(R.id.textViewDays);
        textViewNumberOfDays = findViewById(R.id.textViewNumberOfDays);
        textViewInfo = findViewById(R.id.textViewInfo);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if(extras != null)
        {

        }

    }
}
