package com.seanshend.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.seanshend.view.Circle.Circle;
import com.seanshend.view.dataview.weekview;

import java.util.ArrayList;

public class DataWeekFragment extends BaseFragment {

    weekview w1 = null;
    weekview w2 = null;
    weekview w3 = null;
    weekview w4 = null;

    TextView t1 = null;
    TextView t2 = null;
    TextView t3 = null;
    TextView t4 = null;

    Circle c1 = null;
    Circle c2 = null;
    Circle c3 = null;
    Circle c4 = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data_week, container, false);
        initAll(view);
        return view;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        c1 = (Circle) view.findViewById(R.id.c1);
        c2 = (Circle) view.findViewById(R.id.c2);
        c3 = (Circle) view.findViewById(R.id.c3);
        c4 = (Circle) view.findViewById(R.id.c4);

        w1 = (weekview) view.findViewById(R.id.d1);
        w2 = (weekview) view.findViewById(R.id.d2);
        w3 = (weekview) view.findViewById(R.id.d3);
        w4 = (weekview) view.findViewById(R.id.d4);

        t1 = (TextView) view.findViewById(R.id.t2);
        t2 = (TextView) view.findViewById(R.id.t4);
        t3 = (TextView) view.findViewById(R.id.t6);
        t4 = (TextView) view.findViewById(R.id.t8);
    }

    @Override
    protected void initDatas() {
        super.initDatas();
        c1.setType(1);
        c2.setType(2);
        c3.setType(3);
        c4.setType(4);
        update();
    }

    /*
    * 设置数据
    * type：表示哪一种Score. 1:back， 2：HIPS, 3:WAIST, 4:THIGH
    * data：周一到周日的分数
    * score：总分数
    * */
    private void setData(int type, int[] datas, int score) {
        ArrayList<Integer> data = new ArrayList<>();
        data.add(type);
        for (int j = 0; j < 7; j++) {
            data.add(datas[j]);
        }
        switch (type) {
            case 1:
                w1.setmData(data);
                t1.setText("" + score);
                break;
            case 2:
                w2.setmData(data);
                t2.setText("" + score);
                break;
            case 3:
                w3.setmData(data);
                t3.setText("" + score);
                break;
            case 4:
                w4.setmData(data);
                t4.setText("" + score);
                break;
        }

    }

    @Override
    public void update() {
        setData(1, data.getBackDatas(), data.getwTotalBack());
        setData(2, data.getHipsDatas(), data.getwTotalHips());
        setData(3, data.getWaistDatas(), data.getwTotalWaist());
        setData(4, data.getThighDatas(), data.getwTotalThigh());
    }
}
