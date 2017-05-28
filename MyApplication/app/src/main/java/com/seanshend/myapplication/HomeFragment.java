package com.seanshend.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.seanshend.DataModel;
import com.seanshend.view.chairview.ChairView;
import com.seanshend.view.pieview.PieData;

import java.util.ArrayList;

public class HomeFragment extends BaseFragment implements View.OnClickListener {

    com.seanshend.view.pieview.PieView v1 = null;
    TextView t = null;
    TextView backDegreeTv;
    TextView seatDegreeTv;
    TextView timeTv;
    ChairView chairView = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initAll(view);
        return view;
    }


    @Override
    protected void initViews(View view) {
        super.initViews(view);
        v1 = (com.seanshend.view.pieview.PieView) view.findViewById(R.id.pieView);
        t = (TextView) view.findViewById(R.id.textView2);
        backDegreeTv = (TextView) view.findViewById(R.id.backDegreeTv);
        seatDegreeTv = (TextView) view.findViewById(R.id.seatDegreeTv);
        timeTv = (TextView) view.findViewById(R.id.timeTv);
        chairView = (com.seanshend.view.chairview.ChairView) view.findViewById(R.id.chairView);
    }

    @Override
    protected void initDatas() {
        super.initDatas();
        chairView.Settype(1, data);
        update();
    }

    @Override
    protected void initEvents() {
        super.initEvents();
        v1.setOnClickListener(this);
    }

    public void changeType() {
        if (v1.getType() == 0) {
            v1.Settype(1);
            t.setVisibility(View.GONE);
        } else {
            v1.Settype(0);
            t.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pieView:
                changeType();
        }
    }

    @Override
    public void update() {
        backDegreeTv.setText(data.isConnected() ? data.getBackAngle() + "" : "未连接蓝牙");
        seatDegreeTv.setText(data.isConnected() ? data.getSeatAngle() + "" : "未连接蓝牙");
        timeTv.setText(data.isConnected() ? data.getTime() + "" : "未连接蓝牙");
        ArrayList<PieData> datas = new ArrayList<>();
        PieData pieData = new PieData("Total", (int) data.getTotalScore());
        PieData pieData2 = new PieData("Back", (int) data.getBackScore());
        PieData pieData3 = new PieData("Waist", (int) data.getWaistScore());
        PieData pieData4 = new PieData("Hips", (int) data.getHipsScore());
        PieData pieData5 = new PieData("Thigh", (int) data.getThighScore());
        datas.add(pieData);
        datas.add(pieData2);
        datas.add(pieData3);
        datas.add(pieData4);
        datas.add(pieData5);
        v1.setData(datas);
        chairView.update();
    }
}
