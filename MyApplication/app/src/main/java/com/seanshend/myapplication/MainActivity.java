package com.seanshend.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.seanshend.view.pieview.PieData;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    com.seanshend.view.pieview.PieView v1=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

       v1 = (com.seanshend.view.pieview.PieView)findViewById(R.id.test);

        //data分别为Total, Back, Waist, Hips，Thigh的分值
        int [] data={44,44,90,92,72};
        SetDate(data);




    }

    private void SetDate(int[] data) {
        ArrayList<PieData> datas = new ArrayList<>();
        PieData pieData = new PieData("Total", data[0]);
        PieData pieData2 = new PieData("Back", data[1]);
        PieData pieData3 = new PieData("Waist", data[2]);
        PieData pieData4 = new PieData("Hips", data[3]);
        PieData pieData5 = new PieData("Thigh", data[4]);
        datas.add(pieData);
        datas.add(pieData2);
        datas.add(pieData3);
        datas.add(pieData4);
        datas.add(pieData5);
        v1.setData(datas);
    }

    public void changeType(View view){
        com.seanshend.view.pieview.PieView v1 = (com.seanshend.view.pieview.PieView)findViewById(R.id.test);
        TextView t = (TextView)findViewById(R.id.textView2);
        if(v1.getType()==0) {
            v1.Settype(1);
            t.setVisibility(View.GONE);
        }
        else{
            v1.Settype(0);
            t.setVisibility(View.VISIBLE);
        }
    }

    public void GoProfile(View view){
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);
    }
    public void GoSetting(View view){
        Intent intent = new Intent(this, Setting.class);
        startActivity(intent);
    }
    public void GoData(View view){
        Intent intent = new Intent(this, Data_day.class);
        startActivity(intent);
    }

}
