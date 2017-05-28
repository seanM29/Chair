package com.seanshend.bluetooth;

import android.util.Log;

import com.seanshend.myapplication.MainActivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.Queue;

import static java.lang.Thread.sleep;

public class BlueTooth {
    private BTconnector btConn;
    private InputStream inputStream;
    private OutputStream outputStream;
    private BlueToothListener blueToothListener;

    private Queue<Integer> Q;

    public interface BlueToothListener {
        void onReceiving(int val);

        void onReconnect();

        void onConnectFailed();
    }

    public BlueTooth(BlueToothListener blueToothListener) {
        Q = new LinkedList<>();
        btConn = new BTconnector();
        Log.i(MainActivity.LOG_TAG, "btConn init");
        this.blueToothListener = blueToothListener;
        new Thread(new MainThread()).start();
    }

    public void sendMsg(Integer msg) {
        Q.add(msg);
    }

    public void clearMsgPool() {
        while (!Q.isEmpty())
            Q.remove();
        try {
            while (inputStream.available() > 0)
                inputStream.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class MainThread implements Runnable {
        @Override
        public void run() {
            reconnect();
            Log.i(MainActivity.LOG_TAG, "BTrecvMsgThread run");
            while (true) {
                try {
                    //Send
                    if (!Q.isEmpty()) {
                        Integer msg = Q.remove();
//                        Log.i(MainActivity.LOG_TAG, "Send run : " + msg);
                        outputStream.write(msg);
                    }
                    //Receive
                    if (inputStream.available() > 0) {
                        int msg = inputStream.read();
//                        Log.i(MainActivity.LOG_TAG, "Receive run : " + msg);
                        blueToothListener.onReceiving(msg);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    reconnect();
                }
            }

        }
    }

    private void reconnect() {
        Log.i(MainActivity.LOG_TAG, "Reconnect Run!");
        blueToothListener.onConnectFailed();
        while (true) {
            Log.i(MainActivity.LOG_TAG, "BT disconnected!");
            btConn.enableBT();
            if (btConn.connectBT()) {
                Log.i(MainActivity.LOG_TAG, "reconnect BT successfully!");
                inputStream = btConn.getInputStream();
                outputStream = btConn.getOutputStream();
                //Send begin signal
                blueToothListener.onReconnect();
                break;
            } else {
                Log.i(MainActivity.LOG_TAG, "reconnect BT failed");
            }
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}



