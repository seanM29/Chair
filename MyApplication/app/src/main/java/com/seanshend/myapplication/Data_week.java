package com.seanshend.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.seanshend.view.Circle.Circle;
import com.seanshend.view.dataview.weekview;

import java.util.ArrayList;

public class Data_week extends AppCompatActivity {


    weekview w1 =null;
    weekview w2 =null;
    weekview w3 =null;
    weekview w4 =null;

    TextView t1=null;
    TextView t2=null;
    TextView t3=null;
    TextView t4=null;

    Circle c1=null;
    Circle c2=null;
    Circle c3=null;
    Circle c4=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_week);
        initialize();

        //分别为周一到周日的分数
        int[] datas = {20, 40, 60, 80, 50, 60, 70};
        //总分数
        int score = 2000;


        setData(1, datas, score);
        score--;
        setData(2, datas, score);
        setData(3, datas, score);
        setData(4, datas, score);
    }

    private void initialize() {
        c1 = (Circle) findViewById(R.id.c1);
        c2 = (Circle) findViewById(R.id.c2);
        c3 = (Circle) findViewById(R.id.c3);
        c4 = (Circle) findViewById(R.id.c4);
        c1.setType(1);
        c2.setType(2);
        c3.setType(3);
        c4.setType(4);

        w1 = (weekview) findViewById(R.id.d1);
        w2 = (weekview) findViewById(R.id.d2);
        w3 = (weekview) findViewById(R.id.d3);
        w4 = (weekview) findViewById(R.id.d4);

        t1 = (TextView) findViewById(R.id.t2);
        t2 = (TextView) findViewById(R.id.t4);
        t3 = (TextView) findViewById(R.id.t6);
        t4 = (TextView) findViewById(R.id.t8);


    }


    /*
    * 设置数据
    * type：表示哪一种Score. 1:Back， 2：HIPS, 3:WAIST, 4:THIGH
    * data：周一到周日的分数
    * score：总分数
    * */
    private void setData(int type, int[] datas, int score) {
        ArrayList<Integer> data = new ArrayList<>();
        data.add(type);
        for(int j=0;j<7;j++){
            data.add(datas[j]);
        }
        switch (type){
            case 1:
                w1.setData(data);
                t1.setText(""+score);
                break;
            case 2:
                w2.setData(data);
                t2.setText(""+score);
                break;
            case 3:
                w3.setData(data);
                t3.setText(""+score);
                break;
            case 4:
                w4.setData(data);
                t4.setText(""+score);
                break;
        }

    }

    public void GoDay(View view){
        Intent intent = new Intent(this, Data_day.class);
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
}
