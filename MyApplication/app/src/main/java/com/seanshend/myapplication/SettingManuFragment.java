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

    com.seanshend.view.chairview.ChairView backChairView = null;
    com.seanshend.view.lineview.lineview line1 = null;
    com.seanshend.view.chairview.ChairView seatChairView = null;
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

        backChairView = (com.seanshend.view.chairview.ChairView) view.findViewById(R.id.back2);
        line1 = (com.seanshend.view.lineview.lineview) view.findViewById(R.id.line1);
        seatChairView = (com.seanshend.view.chairview.ChairView) view.findViewById(R.id.seat2);
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
        new Thread(addRunnable).start();
    }

    @Override
    protected void initDatas() {
        super.initDatas();
        backChairView.Settype(3, data);
        line1.setType(0, data.getBackAngle());
        seatChairView.Settype(4, data);
        line2.setType(1, data.getSeatAngle());

        backtext.setText("" + data.getBackAngle());
        seattext.setText("" + data.getSeatAngle());

        update();
    }


    public void start() {
        data.setMode(DataModel.MANU);
        data.setTime(0);
        //todo 修改时间
    }

    public void backminus() {
        if (data.getBackAngle() >= DataModel.MIN_BACK) {
            line1.minAngle();
            data.setBackAngle(data.getBackAngle() - 1);
            backtext.setText("" + data.getBackAngle());
            seatChairView.update();
            backChairView.update();
        }
    }

    public void backadd() {
        if (data.getBackAngle() <= DataModel.MAX_BACK) {
            line1.addAngle();
            data.setBackAngle(data.getBackAngle() + 1);
            backtext.setText("" + data.getBackAngle());
            seatChairView.update();
            backChairView.update();
        }
    }

    public void seatminus() {
        if (data.getSeatAngle() >= DataModel.MIN_SEAT) {
            line2.minAngle();
            data.setSeatAngle(data.getSeatAngle() - 1);
            seattext.setText("" + data.getSeatAngle());
            seatChairView.update();
            backChairView.update();
        }
    }

    public void seatadd() {
        if (data.getSeatAngle() <= DataModel.MAX_SEAT) {
            line2.addAngle();
            data.setSeatAngle(data.getSeatAngle() + 1);
            seattext.setText("" + data.getSeatAngle());
            seatChairView.update();
            backChairView.update();
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
        } else if (event.getAction() == KeyEvent.ACTION_UP) {
            isNeedAdd = false;
        }
        return false;
    }

    private Runnable addRunnable = new Runnable() {

        @Override
        public void run() {
            while (true) {
                if (isNeedAdd) {
                    uiHandler.sendEmptyMessage(0);
                }
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

        String ret = "back:" + data.getBackPressure() + " waist:" + data.getWaistPressure() + " hips:" + data.getHipsPressure() + " thigh:" + data.getThighPressure();
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
