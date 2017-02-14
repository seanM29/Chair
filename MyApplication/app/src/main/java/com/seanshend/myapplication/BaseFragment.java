package com.seanshend.myapplication;


import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.seanshend.DataModel;
import com.seanshend.Observer;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment implements Observer {
    protected MainActivity mActivity;
    protected FragmentManager fm;
    protected DataModel data;

    Handler modifierHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            update();
        }
    };

    protected void initAll(View view) {
        initViews(view);
        initDatas();
        initEvents();
        data.attach(this);
    }

    protected void initViews(View view) {
    }

    protected void initEvents() {
    }

    protected void initDatas() {
        fm = getChildFragmentManager();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (MainActivity) context;
        data = mActivity.getData();
    }

    @Override
    public void onDestroyView() {
        data.detach(this);
        super.onDestroyView();
    }

    @Override
    public void onUpdate() {
        modifierHandler.sendEmptyMessage(0);
    }

    //转到UI线程
    public void update() {
    }
}
