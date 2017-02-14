package com.seanshend.myapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by RenYi on 2017/2/14.
 */

public class SettingAutoFragment extends BaseFragment implements View.OnClickListener {

    public View auto1Layout;
    public View auto2Layout;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting_auto, container, false);
        initAll(view);
        return view;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        auto1Layout = view.findViewById(R.id.auto1Layout);
        auto2Layout = view.findViewById(R.id.auto2Layout);
    }

    @Override
    protected void initEvents() {
        super.initEvents();
        auto1Layout.setOnClickListener(this);
        auto2Layout.setOnClickListener(this);
    }

    @Override
    public void update() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.auto1Layout:
                mActivity.changeFragment(MainActivity.AUTO1);
                break;
            case R.id.auto2Layout:
                mActivity.changeFragment(MainActivity.AUTO2);
                break;
        }
    }
}
