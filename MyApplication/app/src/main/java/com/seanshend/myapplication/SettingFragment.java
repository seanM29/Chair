package com.seanshend.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.seanshend.DataModel;

/**
 * Created by RenYi on 2017/2/14.
 */

public class SettingFragment extends BaseFragment implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup radioGroup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_setting, container, false);

        initAll(view);

        //初始页面 根据上次退出的页面判断
        if (data.getLastPage() == DataModel.AUTO) //Auto mode
            view.findViewById(R.id.rb_auto).performClick();
        else
            view.findViewById(R.id.rb_manu).performClick();
        return view;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        radioGroup = (RadioGroup) view.findViewById(R.id.rg_setting_top);
    }

    @Override
    protected void initEvents() {
        super.initEvents();
        radioGroup.setOnCheckedChangeListener(this);
    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        fm.beginTransaction().replace(R.id.setting_content, getInstanceByIndex(i)).commit();
    }

    public Fragment getInstanceByIndex(int index) {
        Fragment fragment = null;
        switch (index) {
            case R.id.rb_auto:
                fragment = new SettingAutoFragment();
                data.setLastPage(DataModel.AUTO);
                break;
            case R.id.rb_manu:
                fragment = new SettingManuFragment();
                data.setLastPage(DataModel.MANU);
                break;
        }
        return fragment;
    }

}
