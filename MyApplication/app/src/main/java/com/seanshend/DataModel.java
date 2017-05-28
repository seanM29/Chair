package com.seanshend;

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
    private int backPressure; // 6 7
    private int waistPressure; // 4 5
    private int hipsPressure; // 3 2
    private int thighPressure; // 0 1

    //分数
    private double totalScore;
    private double backScore;
    private double waistScore;
    private double hipsScore;
    private double thighScore;

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

    //压力阈值
    public static final int BACK_THRESHOLD = 30;  // -60 90
    public static final int WAIST_THRESHOLD = 10; // -100 -30
    public static final int HIPS_THRESHOLD = 115;  // 170 100
    public static final int THIGH_THRESHOLD = 95; // 80  30

    //分数变化速率
    public static final double BACK_INC_RATE = 0.01;
    public static final double BACK_DEC_RATE = 0.01;
    public static final double WAIST_INC_RATE = 0.01;
    public static final double WAIST_DEC_RATE = 0.01;
    public static final double HIPS_INC_RATE = 0.01;
    public static final double HIPS_DEC_RATE = 0.01;
    public static final double THIGH_INC_RATE = 0.01;
    public static final double THIGH_DEC_RATE = 0.01;


    //上一次设置页面
    private int lastPage;

    //时间
    private int time;

    private int[] pressure;

    //角度范围
    public static final int MAX_BACK = 84;
    public static final int MIN_BACK = 37;
    public static final int MAX_SEAT = 15;
    public static final int MIN_SEAT = 0;

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

    //Getters and setters
    public int getBackAngle() {
        return backAngle;
    }

    public void setBackAngle(int backAngle) {
        if (backAngle >= MIN_BACK && backAngle <= MAX_BACK) {
            this.backAngle = backAngle;
            isAngleChanged = true;
            notifyAllObserver();
        }
    }

    public int getSeatAngle() {
        return seatAngle;
    }

    public void setSeatAngle(int seatAngle) {
        if (seatAngle >= MIN_SEAT && seatAngle <= MAX_SEAT) {
            this.seatAngle = seatAngle;
            isAngleChanged = true;
            notifyAllObserver();
        }
    }

    public boolean isAngleChanged() {
        return isAngleChanged;
    }

    public void setAngleChanged(boolean angleChanged) {
        isAngleChanged = angleChanged;
    }

    public double getTotalScore() {
        totalScore = Math.min(Math.min(Math.min(backScore, waistScore), thighScore), hipsScore);
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
        notifyAllObserver();
    }

    public double getBackScore() {
        return backScore;
    }

    public void setBackScore(double backScore) {
        this.backScore = backScore;
        notifyAllObserver();
    }

    public void updateBackScore() {
        backScore = backScore - ((backPressure - BACK_THRESHOLD) > 0 ? (backPressure - BACK_THRESHOLD) * BACK_DEC_RATE : (backPressure - BACK_THRESHOLD) * BACK_INC_RATE);
        backScore = backScore > 100 ? 100 : backScore;
        backScore = backScore < 0 ? 0 : backScore;
    }

    public double getWaistScore() {
        return waistScore;
    }

    public void setWaistScore(double waistScore) {
        this.waistScore = waistScore;
        notifyAllObserver();
    }

    public void updateWaistScore() {
        waistScore = waistScore - ((waistPressure - WAIST_THRESHOLD) > 0 ? (waistPressure - WAIST_THRESHOLD) * WAIST_DEC_RATE : (waistPressure - WAIST_THRESHOLD) * WAIST_INC_RATE);
        waistScore = waistScore > 100 ? 100 : waistScore;
        waistScore = waistScore < 0 ? 0 : waistScore;
    }


    public double getHipsScore() {
        return hipsScore;
    }

    public void setHipsScore(double hipsScore) {
        this.hipsScore = hipsScore;
        notifyAllObserver();
    }


    public void updateHipsScore() {
        hipsScore = hipsScore - ((hipsPressure - HIPS_THRESHOLD) > 0 ? (hipsPressure - HIPS_THRESHOLD) * HIPS_DEC_RATE : (hipsPressure - HIPS_THRESHOLD) * HIPS_INC_RATE);
        hipsScore = hipsScore > 100 ? 100 : hipsScore;
        hipsScore = hipsScore < 0 ? 0 : hipsScore;
    }

    public double getThighScore() {
        return thighScore;
    }

    public void setThighScore(double thighScore) {
        this.thighScore = thighScore;
        notifyAllObserver();
    }

    public void updateThighScore() {
        thighScore = thighScore - ((thighPressure - THIGH_THRESHOLD) > 0 ? (thighPressure - THIGH_THRESHOLD) * THIGH_DEC_RATE : (thighPressure - THIGH_THRESHOLD) * THIGH_INC_RATE);
        thighScore = thighScore > 100 ? 100 : thighScore;
        thighScore = thighScore < 0 ? 0 : thighScore;
    }


    public int getHipsPressure() {
        return hipsPressure;
    }

    public void setHipsPressure(int hipsPressure) {
        this.hipsPressure = hipsPressure;
    }

    public int getWaistPressure() {
        return waistPressure;
    }

    public void setWaistPressure(int waistPressure) {
        this.waistPressure = waistPressure;
    }

    public int getBackPressure() {
        return backPressure;
    }

    public void setBackPressure(int backPressure) {
        this.backPressure = backPressure;
    }

    public int getThighPressure() {
        return thighPressure;
    }

    public void setThighPressure(int thighPressure) {
        this.thighPressure = thighPressure;
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
//        setBackPressure((pressure[6] + pressure[7]) / 2 - backAngle*3);
//        setWaistPressure((pressure[4] + pressure[5]) / 2 - backAngle*3);
//        setHipsPressure((pressure[2] + pressure[3]) / 2);
//        setThighPressure((pressure[0] + pressure[1]) / 2 );
        setBackPressure(Math.max(pressure[6], pressure[7]));
        setWaistPressure(Math.max(pressure[4] , pressure[5]));
        setHipsPressure(Math.max(pressure[2] , pressure[3]));
        setThighPressure(Math.max(pressure[0] , pressure[1]) );
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