package com.seanshend.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.seanshend.DataModel;

/**
 * Created by RenYi on 2017/2/14.
 */

public class SettingAutoDetailFragment extends BaseFragment implements View.OnClickListener {

    private ProgressBar progressBar;
    private TextView tv = null;
    private TextView backValTv = null;
    private TextView frontValTv = null;
    private TextView modeTv = null;
    private TextView goBack = null;
    com.seanshend.view.chairview.ChairView back;
    com.seanshend.view.chairview.ChairView seat;

    private int backVal;
    private int seatVal;
    private int modeID;
    private String pic;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting_auto_detail, container, false);
        initAll(view);
        return view;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        tv = (TextView) view.findViewById(R.id.tv1);
        backValTv = (TextView) view.findViewById(R.id.backValTv);
        frontValTv = (TextView) view.findViewById(R.id.frontValTv);
        modeTv = (TextView) view.findViewById(R.id.modeTv);
        goBack = (TextView) view.findViewById(R.id.goBack);

        back = (com.seanshend.view.chairview.ChairView) view.findViewById(R.id.Back);
        seat = (com.seanshend.view.chairview.ChairView) view.findViewById(R.id.Front);
    }

    @Override
    protected void initEvents() {
        super.initEvents();
        progressBar.setOnClickListener(this);
        tv.setOnClickListener(this);
        goBack.setOnClickListener(this);
    }

    @Override
    protected void initDatas() {
        super.initDatas();
        Bundle bundle = getArguments();
        backVal = bundle.getInt("back");
        seatVal = bundle.getInt("seat");
        pic = bundle.getString("pic");
        modeID = bundle.getInt("modeID");

        back.Settype(1, backVal);
        backValTv.setText("" + backVal);

        seat.Settype(2, 360 - seatVal);
        frontValTv.setText("" + seatVal);

        modeTv.setText(bundle.getString("mode"));
        update();
    }

    public void back() {
        mActivity.changeFragment(R.id.rb_setting);
    }

    public void start() {
        data.setBackAngle(backVal);
        data.setSeatAngle(seatVal);
        data.setMode(modeID);
        data.setTime(0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.goBack:
                back();
                break;
            case R.id.tv1:
            case R.id.progressBar:
                start();
                break;
        }
    }

    @Override
    public void update() {
        if (data.getTime() >= 90) {
            data.setMode(DataModel.NONE);
        }

        //开启手动模式后显示时间条
        if (data.getMode() == modeID)
            progressBar.setProgress(90 - data.getTime());
        else
            progressBar.setProgress(90);

        //手动模式开启，50min后显示剩余时间
        if (data.getTime() > 50 && data.getMode() == modeID) {
            tv.setTextColor(0xff4c9fff);
            String strTime = (90 - data.getTime()) + " Min";
            tv.setText(strTime);
        } else if (data.getMode() == modeID) {//手动模式刚开启50min以内，不显示文字
            tv.setText("");
        } else {//未开启手动模式，显示start按钮
            tv.setText("START");
            tv.setTextColor(0xffffffff);
        }
    }
}
