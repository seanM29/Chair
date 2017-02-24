package com.seanshend;

import android.util.Log;

import com.seanshend.myapplication.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RenYi on 2017/2/14.
 */

public class DataModel implements Subject {
    //角度
    private int backAngle;
    private int seatAngle;
    private boolean isAngleChanged;

    //压力
    private int backPressure;
    private int waistPressure;
    private int hipPressure;
    private int thighPressure;

    //分数
    private int totalScore;
    private int backScore;
    private int waistScore;
    private int hipsScore;
    private int thighScore;

    //周分数
    private int[] backDatas;
    private int[] waistDatas;
    private int[] hipsDatas;
    private int[] thighDatas;

    //周总分
    private int wTotalBack;
    private int wTotalWaist;
    private int wTotalHips;
    private int wTotalThigh;

    //日分数
    private int dTotalScore;
    private int dBackScore;
    private int dWaistScore;
    private int dHipsScore;
    private int dThighScore;

    //模式
    public static final int AUTO = 0;
    public static final int AUTO_M1 = 1;
    public static final int AUTO_M2 = 2;
    public static final int NONE = 3;
    public static final int MANU = 4;

    private int mode;

    //是否连接蓝牙
    private boolean isConnected;

    //操作信号
    public static final int CHANGE_BACK = 0;
    public static final int CHANGE_SEAT = 1;

    //接收信号
    public static final int RECEIVE_BACK = 0;
    public static final int RECEIVE_WAIST = 1;
    public static final int RECEIVE_HIPS = 2;
    public static final int RECEIVE_THIGH = 3;

    //上一次设置页面
    private int lastPage;

    //时间
    private int time;

    private int[] pressure;

    //观察者列表
    private List<Observer> observerList = new ArrayList<>();

    @Override
    public void attach(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyAllObserver() {
        for (Observer observer : observerList) {
            observer.onUpdate();
        }
    }

    private void calcScore() {
        this.setBackScore(0);
        this.setWaistScore(0);
        this.setdHipsScore(0);
        this.setThighScore(0);
    }

    //Getters and setters
    public int getBackAngle() {
        return backAngle;
    }

    public void setBackAngle(int backAngle) {
        this.backAngle = backAngle;
        isAngleChanged = true;
        notifyAllObserver();
    }

    public int getSeatAngle() {
        return seatAngle;
    }

    public void setSeatAngle(int seatAngle) {
        this.seatAngle = seatAngle;
        isAngleChanged = true;
        notifyAllObserver();
    }

    public boolean isAngleChanged() {
        return isAngleChanged;
    }

    public void setAngleChanged(boolean angleChanged) {
        isAngleChanged = angleChanged;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
        notifyAllObserver();
    }

    public int getBackScore() {
        return backScore;
    }

    public void setBackScore(int backScore) {
        this.backScore = backScore;
        notifyAllObserver();
    }

    public int getWaistScore() {
        return waistScore;
    }

    public void setWaistScore(int waistScore) {
        this.waistScore = waistScore;
        notifyAllObserver();
    }

    public int getHipsScore() {
        return hipsScore;
    }

    public void setHipsScore(int hipsScore) {
        this.hipsScore = hipsScore;
        notifyAllObserver();
    }

    public int getThighScore() {
        return thighScore;
    }

    public void setThighScore(int thighScore) {
        this.thighScore = thighScore;
        notifyAllObserver();
    }

    public int getHipPressure() {
        return hipPressure;
    }

    public void setHipPressure(int hipPressure) {
        this.hipPressure = hipPressure;
        calcScore();
    }

    public int getWaistPressure() {
        return waistPressure;
    }

    public void setWaistPressure(int waistPressure) {
        this.waistPressure = waistPressure;
        calcScore();
    }

    public int getBackPressure() {
        return backPressure;
    }

    public void setBackPressure(int backPressure) {
        this.backPressure = backPressure;
        calcScore();
    }

    public int getThighPressure() {
        return thighPressure;
    }

    public void setThighPressure(int thighPressure) {
        this.thighPressure = thighPressure;
        calcScore();
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
        notifyAllObserver();
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
        notifyAllObserver();
    }


    public int getdTotalScore() {
        return dTotalScore;
    }

    public void setdTotalScore(int dTotalScore) {
        this.dTotalScore = dTotalScore;
        notifyAllObserver();
    }

    public int getdBackScore() {
        return dBackScore;
    }

    public void setdBackScore(int dBackScore) {
        this.dBackScore = dBackScore;
        notifyAllObserver();
    }

    public int getdWaistScore() {
        return dWaistScore;
    }

    public void setdWaistScore(int dWaistScore) {
        this.dWaistScore = dWaistScore;
        notifyAllObserver();
    }

    public int getdHipsScore() {
        return dHipsScore;
    }

    public void setdHipsScore(int dHipsScore) {
        this.dHipsScore = dHipsScore;
        notifyAllObserver();
    }

    public int getdThighScore() {
        return dThighScore;
    }

    public void setdThighScore(int dThighScore) {
        this.dThighScore = dThighScore;
        notifyAllObserver();
    }

    public int[] getThighDatas() {
        return thighDatas;
    }

    public void setThighDatas(int[] thighDatas) {
        this.thighDatas = thighDatas;
        notifyAllObserver();
    }

    public int[] getBackDatas() {
        return backDatas;
    }

    public void setBackDatas(int[] backDatas) {
        this.backDatas = backDatas;
        notifyAllObserver();
    }

    public int[] getWaistDatas() {
        return waistDatas;
    }

    public void setWaistDatas(int[] waistDatas) {
        this.waistDatas = waistDatas;
        notifyAllObserver();
    }

    public int[] getHipsDatas() {
        return hipsDatas;
    }

    public void setHipsDatas(int[] hipsDatas) {
        this.hipsDatas = hipsDatas;
        notifyAllObserver();
    }

    public int getwTotalBack() {
        return wTotalBack;
    }

    public void setwTotalBack(int wTotalBack) {
        this.wTotalBack = wTotalBack;
        notifyAllObserver();
    }

    public int getwTotalWaist() {
        return wTotalWaist;
    }

    public void setwTotalWaist(int wTotalWaist) {
        this.wTotalWaist = wTotalWaist;
        notifyAllObserver();
    }

    public int getwTotalHips() {
        return wTotalHips;
    }

    public void setwTotalHips(int wTotalHips) {
        this.wTotalHips = wTotalHips;
        notifyAllObserver();
    }

    public int getwTotalThigh() {
        return wTotalThigh;
    }

    public void setwTotalThigh(int wTotalThigh) {
        this.wTotalThigh = wTotalThigh;
        notifyAllObserver();
    }

    public void setPressure(int[] pressure) {
        this.pressure = pressure;
        notifyAllObserver();
    }

    public int[] getPressure() {
        return pressure;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
        notifyAllObserver();
    }
}