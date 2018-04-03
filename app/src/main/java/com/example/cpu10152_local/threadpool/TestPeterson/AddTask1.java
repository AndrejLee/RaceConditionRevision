package com.example.cpu10152_local.threadpool.TestPeterson;

import android.util.Log;

import com.example.cpu10152_local.threadpool.Global;

/**
 * Created by cpu10152-local on 28/03/2018.
 */

public class AddTask1 implements Runnable {
    private static final String TAG = "TAG";

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(8000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Global.sInteresse[1] = 1;
            Global.sTurn = 0;

            while (Global.sTurn == 0 && Global.sInteresse[0] == 1);
            Log.d(TAG, "Thread 2 In CS");
            Global.sCount++;
            Log.d(TAG, "Thread 2 Current Count: " + Global.sCount);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Global.sInteresse[1] = 0;
            Log.d(TAG, "Thread 2 Out CS");
        }
    }
}
