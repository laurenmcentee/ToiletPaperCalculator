package com.example.toiletpapercalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ParametersActivity extends AppCompatActivity {

    // CONSTANTS
    final int sheetPerPoopDefault = 25;
    final int frequencyDefault = 1;
    final String nullError = "You must enter a value in each category";
    final String zeroError = "Entries must be greater than zero";

    //VARIABLES
    int sheetPerPoopPref = 0;
    int frequencyPref = 0;

    //WIDGETS
    EditText editTextSheetPreference;
    EditText editTextFrequency;
    Button buttonReset;
    Button buttonUpdate;
    Button buttonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parameters);

        editTextSheetPreference = findViewById(R.id.editTextSheetPreference);
        editTextFrequency       = findViewById(R.id.editTextFrequency);
        buttonBack              = findViewById(R.id.buttonBack);
        buttonReset             = findViewById(R.id.buttonReset);
        buttonUpdate            = findViewById(R.id.buttonUpdate);

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(editTextSheetPreference == null || editTextFrequency == null )
                {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            nullError, Toast.LENGTH_LONG);
                    toast.show();
                }

                //consider turning into doubles
                frequencyPref = Integer.parseInt(editTextFrequency.getText().toString());
                sheetPerPoopPref = Integer.parseInt(editTextSheetPreference.getText().toString());

                if(sheetPerPoopPref == 0 ||  frequencyPref == 0 )
                {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            zeroError, Toast.LENGTH_LONG);
                    toast.show();
                }

                //get rid of toast for final copy
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Values are now: " + frequencyPref + sheetPerPoopPref, Toast.LENGTH_LONG);
                toast.show();


                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("frequencyPref", frequencyPref);
                    resultIntent.putExtra("sheetPerPoopPref", sheetPerPoopPref);
                    setResult(RESULT_OK, resultIntent);
                    finish();
            }
        });


        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                frequencyPref = frequencyDefault;
                sheetPerPoopPref = sheetPerPoopDefault;

            }
        });


    }
}
