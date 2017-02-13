package com.seanshend.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.seanshend.view.Circle.Circle;
import com.seanshend.view.dataview.dayview;

import java.util.ArrayList;

public class Data_day extends AppCompatActivity {


   dayview data_day = null;
    Circle c1=null;
    Circle c2=null;
    Circle c3=null;
    Circle c4=null;
    TextView t1=null;
    TextView t2=null;
    TextView t3=null;
    TextView t4=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_day);
        initialize();


        //data分别为Total, Back, Waist, Hips，Thigh的分值
        int []datas = {0,80,70,30,90};
        setData(datas);

    }



    private void initialize() {
        data_day = (dayview)findViewById(R.id.data_day);
        c1=(Circle)findViewById(R.id.c1);
        c2=(Circle)findViewById(R.id.c2);
        c3=(Circle)findViewById(R.id.c3);
        c4=(Circle)findViewById(R.id.c4);
        t1=(TextView) findViewById(R.id.t1);
        t2=(TextView) findViewById(R.id.t2);
        t3=(TextView) findViewById(R.id.t3);
        t4=(TextView) findViewById(R.id.t4);


    }



    public void GoWeek(View view){
        Intent intent = new Intent(this, Data_week.class);
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
    public void GoSetting(View view){
        Intent intent = new Intent(this, Setting.class);
        startActivity(intent);
    }

    public void setData(int[] data) {

        ArrayList<Integer> A = new ArrayList<>();
        A.add(data[0]);
        A.add(data[1]);
        A.add(data[2]);
        A.add(data[3]);
        A.add(data[4]);
        data_day.setData(A);
        c1.setType(1);
        c2.setType(2);
        c3.setType(3);
        c4.setType(4);
        t1.setText(""+data[1]);
        t2.setText(""+data[2]);
        t3.setText(""+data[3]);
        t4.setText(""+data[4]);
    }
}
