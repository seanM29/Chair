package com.seanshend.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Setting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
    }


    public void GoAuto1(View view){
        Intent intent = new Intent(this, Setting_Auto1.class);
        startActivity(intent);
    }
    public void GoAuto2(View view){
        Intent intent = new Intent(this, Auto2.class);
        startActivity(intent);
    }
    public void GoManu(View view){
        Intent intent = new Intent(this, setting_manu.class);
        startActivity(intent);
    }
    public void GoProfile(View view){
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);
    }
    public void GoHome(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void GoData(View view){
        Intent intent = new Intent(this, Data_day.class);
        startActivity(intent);
    }
}
