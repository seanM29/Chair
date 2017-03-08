package com.seanshend.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.seanshend.DataModel;

public class SettingManuFragment extends BaseFragment implements View.OnClickListener, View.OnTouchListener {

    TextView backtext = null;
    TextView seattext = null;
    TextView pressure = null;

    com.seanshend.view.chairview.ChairView back = null;
    com.seanshend.view.lineview.lineview line1 = null;
    com.seanshend.view.chairview.ChairView seat = null;
    com.seanshend.view.lineview.lineview line2 = null;

    Button backMinusBtn = null;
    Button backAddBtn = null;
    Button seatMinusBtn = null;
    Button seatAddBtn = null;


    private ProgressBar progressBar = null;
    private TextView tv = null;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting_manu, container, false);
        initAll(view);
        return view;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        tv = (TextView) view.findViewById(R.id.tv1);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        backtext = (TextView) view.findViewById(R.id.textView2);
        seattext = (TextView) view.findViewById(R.id.textView3);
        pressure = (TextView) view.findViewById(R.id.textView9);

        back = (com.seanshend.view.chairview.ChairView) view.findViewById(R.id.back2);
        line1 = (com.seanshend.view.lineview.lineview) view.findViewById(R.id.line1);
        seat = (com.seanshend.view.chairview.ChairView) view.findViewById(R.id.seat2);
        line2 = (com.seanshend.view.lineview.lineview) view.findViewById(R.id.line2);

        backMinusBtn = (Button) view.findViewById(R.id.backMinusBtn);
        backAddBtn = (Button) view.findViewById(R.id.backAddBtn);
        seatMinusBtn = (Button) view.findViewById(R.id.seatMinusBtn);
        seatAddBtn = (Button) view.findViewById(R.id.seatAddBtn);
    }

    @Override
    protected void initEvents() {
        super.initEvents();
        progressBar.setOnClickListener(this);
        tv.setOnClickListener(this);

        backMinusBtn.setOnTouchListener(this);
        backAddBtn.setOnTouchListener(this);
        seatMinusBtn.setOnTouchListener(this);
        seatAddBtn.setOnTouchListener(this);
    }

    @Override
    protected void initDatas() {
        super.initDatas();
        back.Settype(3, data.getBackAngle());
        line1.setType(0, data.getBackAngle());
        seat.Settype(4, 360 - data.getSeatAngle());
        line2.setType(1, data.getSeatAngle());

        backtext.setText("" + back.getAngle());
        seattext.setText("" + (360 - seat.getAngle()));

        update();
    }


    public void start() {
        data.setMode(DataModel.MANU);
        data.setTime(0);
        //todo 修改时间
    }

    public void backminus() {
        if (back.getAngle() > 41) {
            back.minAngle();
            line1.minAngle();
            data.setBackAngle(back.getAngle());
            backtext.setText("" + back.getAngle());
        }
    }

    public void backadd() {
        if (back.getAngle() < 81) {
            back.addAngle();
            line1.addAngle();
            data.setBackAngle(back.getAngle());
            backtext.setText("" + back.getAngle());
        }
    }

    public void seatminus() {
        if (360 - seat.getAngle() > 4) {
            seat.addAngle();
            line2.minAngle();
            data.setSeatAngle(360 - seat.getAngle());
            seattext.setText("" + (360 - seat.getAngle()));

        }
    }

    public void seatadd() {
        if (360 - seat.getAngle() < 26) {
            seat.minAngle();
            line2.addAngle();
            data.setSeatAngle(360 - seat.getAngle());
            seattext.setText("" + (360 - seat.getAngle()));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.backMinusBtn:
                backminus();
                break;
            case R.id.backAddBtn:
                backadd();
                break;
            case R.id.seatMinusBtn:
                seatminus();
                break;
            case R.id.seatAddBtn:
                seatadd();
                break;
            case R.id.progressBar:
            case R.id.tv1:
                start();
                break;
        }
    }

    //按住持续增减角度
    boolean isNeedAdd = false;
    View btnTouching = null;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            isNeedAdd = true;
            btnTouching = v;
            new Thread(addRunnable).start();
        } else if (event.getAction() == KeyEvent.ACTION_UP) {
            isNeedAdd = false;
        }
        return false;
    }

    private Runnable addRunnable = new Runnable() {

        @Override
        public void run() {
            while (isNeedAdd) {
                uiHandler.sendEmptyMessage(0);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    };

    private Handler uiHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            onClick(btnTouching);
        }
    };

    @Override
    public void update() {
        int[] receiveData = data.getPressure();

        String ret = "p1:" + receiveData[0] + " p2:" + receiveData[1] + "  p3:" + receiveData[2] + " p4:" + receiveData[3] + " p5:" + receiveData[4] + " p6:" + receiveData[5] + " p7:" + receiveData[6] + " p8:" + receiveData[7];
        pressure.setText(ret);

        if (data.getTime() >= 90) {
            data.setMode(DataModel.NONE);
        }

        //开启手动模式后显示时间条
        if (data.getMode() == DataModel.MANU)
            progressBar.setProgress(90 - data.getTime());
        else
            progressBar.setProgress(90);

        //手动模式开启，50min后显示剩余时间
        if (data.getTime() > 50 && data.getMode() == DataModel.MANU) {
            tv.setTextColor(0xff4c9fff);
            String strTime = (90 - data.getTime()) + " Min";
            tv.setText(strTime);
        } else if (data.getMode() == DataModel.MANU) {//手动模式刚开启50min以内，不显示文字
            tv.setText("");
        } else {//未开启手动模式，显示start按钮
            tv.setTextColor(0xffffffff);
            tv.setText("START");
        }
    }
}
