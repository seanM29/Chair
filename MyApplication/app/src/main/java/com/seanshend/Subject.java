package com.seanshend;

/**
 * Created by RenYi on 2017/2/14.
 * 被观察者类
 */

public interface Subject {

    /**
     * 增加观察者
     * @param observer
     */
    public void attach(Observer observer);

    /**
     * 删除观察者
     * @param observer
     */
    public void detach(Observer observer);

    /**
     * 通知所有观察者
     */
    public void notifyAllObserver();
}
