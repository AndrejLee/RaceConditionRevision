package com.example.cpu10152_local.threadpool.TestThreadPool;

import android.util.Log;

/**
 * Created by cpu10152-local on 27/03/2018.
 */

public class Task implements Runnable {

    private static final String TAG = "TAG";
    private int num;

    public Task(int n){
        num = n;
    }

    @Override
    public void run() {
        Log.d(TAG, "Task " + num + " is running!" );
    }
}
