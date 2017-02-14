package com.seanshend.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

/**
 * Created by RenYi on 2017/2/14.
 */

public class DataFragment extends BaseFragment implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup radioGroup;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_data, container, false);

        initAll(view);
        view.findViewById(R.id.rb_day).performClick();
        return view;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        radioGroup = (RadioGroup) view.findViewById(R.id.rg_data_top);
    }

    @Override
    protected void initEvents() {
        super.initEvents();
        radioGroup.setOnCheckedChangeListener(this);
    }


    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        fm.beginTransaction().replace(R.id.data_content, getInstanceByIndex(i)).commit();
    }

    public static Fragment getInstanceByIndex(int index) {
        Fragment fragment = null;
        switch (index) {
            case R.id.rb_day:
                fragment = new DataDayFragment();
                break;
            case R.id.rb_week:
                fragment = new DataWeekFragment();
                break;
        }
        return fragment;
    }

}
