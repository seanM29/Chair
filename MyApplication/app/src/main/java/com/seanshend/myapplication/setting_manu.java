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

public class setting_manu extends AppCompatActivity {

    TextView backtext =null;
    com.seanshend.view.chairview.ChairView back=null;
    com.seanshend.view.lineview.lineview line1=null;

    TextView seattext =null;
    com.seanshend.view.chairview.ChairView seat=null;
    com.seanshend.view.lineview.lineview line2=null;


    private ProgressBar progressBar;
    private TextView tv=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_manu);
        init();
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        tv = (TextView)findViewById(R.id.tv1);
    }
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            tv.setTextColor(0xff4c9fff);
            String tmp =""+ msg.what;
            tmp = tmp+"min";
            tv.setText(tmp);

        }
    };
    public void GoAuto(View view){
        Intent intent = new Intent(this, Setting.class);
        startActivity(intent);
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
    private void init() {

         back = (com.seanshend.view.chairview.ChairView)findViewById(R.id.back2);
        back.Settype(3,50);
         line1 =(com.seanshend.view.lineview.lineview)findViewById(R.id.line1);
        line1.setType(0);
        backtext=(TextView)findViewById(R.id.textView2);
        seat = (com.seanshend.view.chairview.ChairView)findViewById(R.id.seat2);
        seat.Settype(4,330);
        line2 =(com.seanshend.view.lineview.lineview)findViewById(R.id.line2);
        line2.setType(0,30);
        seattext=(TextView)findViewById(R.id.textView3);
    }
    public void backminus(View view){
        back.minAngle();
        line1.minAngle();
        backtext.setText(""+back.getAngle());
    }
    public void backadd(View view){
        back.addAngle();
        line1.addAngle();
        backtext.setText(""+back.getAngle());
    }
    public void seatminus(View view){
        seat.addAngle();
        line2.minAngle();
        seattext.setText(""+(360-seat.getAngle()));
    }
    public void seatadd(View view){
        seat.minAngle();
        line2.addAngle();
        seattext.setText(""+(360-seat.getAngle()));
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
