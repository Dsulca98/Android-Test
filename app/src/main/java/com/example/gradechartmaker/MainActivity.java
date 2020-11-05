package com.example.gradechartmaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
//This is only the welcome screen
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    //Once the button is clicked in this activity, it will trigger the new activity
    public void continueToInputActivity(View view) {
        //Intent to call a new activity
        Intent intentGradeInput= new Intent(MainActivity.this,GradeInputActivity.class);
        startActivity(intentGradeInput);
    }
}