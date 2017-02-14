package com.seanshend.myapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.RadioGroup;

import com.seanshend.DataModel;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    private RadioGroup radioGroup;
    private FragmentManager fm;
    private DataModel data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initDatas();//初始化数据
        initViews();//初始化控件
        initEvents();//初始化事件
        findViewById(R.id.rb_home).performClick();

    }

    private void initDatas() {
        fm = getSupportFragmentManager();
        data = new DataModel();

        //即时数据
        data.setBackScore(100);
        data.setHipsScore(100);
        data.setThighScore(100);
        data.setWaistScore(100);
        data.setTotalScore(100);
        data.setBackAngle(60);
        data.setSeatAngle(40);
        data.setTime(0);

        //日
        data.setdBackScore(100);
        data.setdHipsScore(100);
        data.setdThighScore(100);
        data.setdWaistScore(100);
        data.setdTotalScore(100);

        //周
        int[] backDatas = {50, 60, 70, 60, 50, 40, 30};
        int[] hipsDatas = {50, 60, 70, 60, 50, 40, 30};
        int[] waistDatas = {50, 60, 70, 60, 50, 40, 30};
        int[] thighDatas = {50, 60, 70, 60, 50, 40, 30};
        data.setBackDatas(backDatas);
        data.setHipsDatas(hipsDatas);
        data.setWaistDatas(waistDatas);
        data.setThighDatas(thighDatas);
        data.setwTotalBack(1000);
        data.setwTotalHips(1000);
        data.setwTotalWaist(1000);
        data.setwTotalThigh(1000);

        //模式
        data.setLastPage(DataModel.AUTO);

        //时钟
        new ClockThread();
    }

    //初始化控件
    private void initViews() {
        radioGroup = (RadioGroup) findViewById(R.id.rg_tab);
    }

    private void initEvents() {
        radioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        changeFragment(i);
    }

    public void changeFragment(int i) {
        fm.beginTransaction().replace(R.id.main_content, getInstanceByIndex(i)).commit();
    }

    static final int AUTO1 = -1;
    static final int AUTO2 = -2;

    public Fragment getInstanceByIndex(int index) {
        Fragment fragment = null;
        Bundle bundle = new Bundle();
        switch (index) {
            case R.id.rb_home:
                fragment = new HomeFragment();
                break;
            case R.id.rb_data:
                fragment = new DataFragment();
                break;
            case R.id.rb_setting:
                fragment = new SettingFragment();
                break;
            case R.id.rb_user:
                fragment = new ProfileFragment();
                break;
            case AUTO1:
                fragment = new SettingAutoDetailFragment();
                bundle.putInt("back", 50);
                bundle.putInt("seat", 30);
                bundle.putString("mode", "Healthy-back");
                bundle.putString("pic", "");
                bundle.putInt("modeID", DataModel.AUTO_M1);
                fragment.setArguments(bundle);
                break;
            case AUTO2:
                fragment = new SettingAutoDetailFragment();
                bundle.putInt("back", 15);
                bundle.putInt("seat", 60);
                bundle.putString("mode", "Amazing");
                bundle.putString("pic", "");
                bundle.putInt("modeID", DataModel.AUTO_M2);
                fragment.setArguments(bundle);
                break;
        }
        return fragment;
    }

    //时钟线程
    class ClockThread implements Runnable {
        Thread t;

        ClockThread() {
            t = new Thread(this);
            t.start();
        }

        @Override
        public void run() {
            while (true) {
                try {
                    //5s
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                data.setTime(data.getTime() + 1);
            }
        }
    }


    public DataModel getData() {
        return data;
    }
}