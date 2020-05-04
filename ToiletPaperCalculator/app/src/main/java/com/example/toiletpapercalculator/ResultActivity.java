package com.example.toiletpapercalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {

    TextView textViewNumberOfDays;
    TextView textViewDays;
    TextView textViewInfo;
    ImageView imageViewTP;
    Button   buttonBack;

    int daysOfTP = 0;
    int rollQuant = 0;
    int houseMembers = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textViewDays = findViewById(R.id.textViewDays);
        textViewNumberOfDays = findViewById(R.id.textViewNumberOfDays);
        textViewInfo = findViewById(R.id.textViewInfo);
        imageViewTP = findViewById(R.id.imageViewTP);
        buttonBack = findViewById(R.id.buttonBack);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        //Checks for null values
        if(extras != null)
        {
            if(extras.containsKey("daysOfTP"))
            {
                daysOfTP =+ extras.getInt("daysOfTP", 0);
            }
            if(extras.containsKey("rollQuant"))
            {
                rollQuant =+ extras.getInt("rollQuant", 0);
            }
            if(extras.containsKey("houseMembers"))
            {
                houseMembers =+ extras.getInt("houseMembers", 0);
            }
        }
        else
        {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Entries are Null", Toast.LENGTH_LONG);
            toast.show();
        }


        // Determines what illustration to display based on number of days
        if(daysOfTP > 30)
        {
            imageViewTP.setImageResource(R.drawable.tphigh);
        }
        if(daysOfTP >= 7 && daysOfTP <= 30)
        {
            imageViewTP.setImageResource(R.drawable.tpmedium);
        }
        if(daysOfTP < 7)
        {
            imageViewTP.setImageResource(R.drawable.tplow);
        }



        //Create summary string
        String summary = "";
        summary =+ rollQuant + " rolls of toilet paper will last a household of "
                             + houseMembers + ", about "
                             + daysOfTP + " days.";

        textViewInfo.setText(summary);
        textViewNumberOfDays.setText(String.valueOf(daysOfTP));

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

    }
}
