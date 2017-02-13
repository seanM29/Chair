package com.seanshend.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class Setting_Auto1 extends AppCompatActivity {
    private ProgressBar progressBar;
    private TextView tv=null;


    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            tv.setTextColor(0xff4c9fff);
                String tmp =""+ msg.what;
                tmp = tmp+"min";
                tv.setText(tmp);

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting__auto1);
        init();
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        tv = (TextView)findViewById(R.id.tv1);
    }


    public void Back(View view){
        Intent intent = new Intent(this, Setting.class);
        startActivity(intent);
    }

    private void init() {

        com.seanshend.view.chairview.ChairView back = (com.seanshend.view.chairview.ChairView)findViewById(R.id.Back);
        back.Settype(1,50);
        com.seanshend.view.chairview.ChairView front = (com.seanshend.view.chairview.ChairView)findViewById(R.id.Front);
        front.Settype(2,330);
    }

    public void start(View view){
        tv.setText("");
        new Thread(){
            @Override
            public void run() {

                for (int i=0;i<90;i++){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    progressBar.incrementProgressBy(-1);
                    Log.i("i:",""+i);
                    if(i>=90*0.6){
                        int tmp = 90-i;

                        Message msg = new Message();
                        msg.what = tmp;
                        handler.sendMessage(msg);
                    }
                }
            }
        }.start();
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
