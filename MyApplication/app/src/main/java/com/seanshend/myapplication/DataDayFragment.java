package com.seanshend.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.seanshend.Observer;
import com.seanshend.view.Circle.Circle;
import com.seanshend.view.dataview.dayview;

import java.util.ArrayList;

public class DataDayFragment extends BaseFragment {

    dayview data_day = null;
    Circle c1 = null;
    Circle c2 = null;
    Circle c3 = null;
    Circle c4 = null;
    TextView t1 = null;
    TextView t2 = null;
    TextView t3 = null;
    TextView t4 = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data_day, container, false);
        initAll(view);
        return view;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        data_day = (dayview) view.findViewById(R.id.data_day);
        c1 = (Circle) view.findViewById(R.id.c1);
        c2 = (Circle) view.findViewById(R.id.c2);
        c3 = (Circle) view.findViewById(R.id.c3);
        c4 = (Circle) view.findViewById(R.id.c4);
        t1 = (TextView) view.findViewById(R.id.t1);
        t2 = (TextView) view.findViewById(R.id.t2);
        t3 = (TextView) view.findViewById(R.id.t3);
        t4 = (TextView) view.findViewById(R.id.t4);
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

    @Override
    public void update() {
        ArrayList<Integer> A = new ArrayList<>();
        A.add(data.getdTotalScore());
        A.add(data.getdBackScore());
        A.add(data.getdHipsScore());
        A.add(data.getdWaistScore());
        A.add(data.getdThighScore());
        data_day.setData(A);
        t1.setText("" + data.getdBackScore());
        t2.setText("" + data.getdHipsScore());
        t3.setText("" + data.getdWaistScore());
        t4.setText("" + data.getdThighScore());
    }
}
