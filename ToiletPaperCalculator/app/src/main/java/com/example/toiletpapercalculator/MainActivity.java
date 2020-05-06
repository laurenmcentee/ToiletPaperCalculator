package com.example.toiletpapercalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //CONSTANTS
    final String nullError = "You must enter a value in each category";
    final String zeroError = "Inputs cannot be zero";

    //VARIABLES
    int sheetsPerPoop = 25;
    int poopsPerDay = 1;  //less than 40% of people poop everyday consider changing number

    int houseMembers = 0;
    int rollQuant = 0;
    int sheetQuant = 0;

    //WIDGETS

    EditText editTextRolls;
    EditText editTextHouseMembers;
    EditText editTextSheets;
    //Spinner

    Button buttonClear;
    Button buttonCalculate;
    Button buttonMoreParameters;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       editTextHouseMembers = findViewById(R.id.editTextHouseMembers);
       editTextRolls = findViewById(R.id.editTextRolls);
       editTextSheets = findViewById(R.id.editTextSheets);
       buttonClear = findViewById(R.id.buttonClear);
       buttonCalculate = findViewById(R.id.buttonCalculate);
       buttonMoreParameters = findViewById(R.id.buttonMoreParameters);

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              clearAll();
            }
        });

        buttonMoreParameters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ParametersActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    boolean keepGoing = false;

                    rollQuant = Integer.parseInt(editTextRolls.getText().toString());
                    sheetQuant = Integer.parseInt(editTextSheets.getText().toString());
                    houseMembers = Integer.parseInt(editTextHouseMembers.getText().toString());

                    if (rollQuant == 0 || sheetQuant == 0 || houseMembers == 0) {
                        Toast toast = Toast.makeText(getApplicationContext(),
                                zeroError, Toast.LENGTH_LONG);
                        toast.show();

                        editTextSheets.setText("");
                        editTextHouseMembers.setText("");
                        editTextRolls.setText("");
                        editTextRolls.requestFocus();
                    }
                    else{
                        keepGoing = true;
                    }

                    if(keepGoing = true) {
                        //Gets Results
                        int daysOfTP = getResult(rollQuant, sheetQuant, houseMembers);

                        Bundle extras = new Bundle();
                        extras.putInt("daysOfTP", daysOfTP);
                        extras.putInt("rollQuant", rollQuant);
                        extras.putInt("houseMembers", houseMembers);
                        Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                        intent.putExtras(extras);
                        startActivity(intent);
                    }
                }
        });
    } //end of OnCreate

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                sheetsPerPoop = data.getIntExtra("sheetPerPoopPref", 0);
                poopsPerDay = data.getIntExtra("frequencyPref", 0);

                Toast toast = Toast.makeText(getApplicationContext(),
                        "Preferences have been changed to: " + sheetsPerPoop + "and" + poopsPerDay , Toast.LENGTH_LONG);
                toast.show();

            }
        }
    }


    public void clearAll() {
        editTextRolls.setText("");
        editTextSheets.setText("");
        editTextHouseMembers.setText("");
        editTextRolls.requestFocus();
        houseMembers = 0;
        rollQuant = 0;
        sheetQuant = 0;
    }

    public int getResult(int rq,int sq, int hm)
    {
        int totalSheets = rq * sq;                      //number of rolls * number of sheets = total sheets
        int sheetUsage = sheetsPerPoop * poopsPerDay;   // sheets usage * times gone per day = sheets used each day
        int result = totalSheets / sheetUsage;          // total sheets / sheet usage = how long sheets will last
        return result / hm;                             // takes into account number of household members
    }


} //End of Class
