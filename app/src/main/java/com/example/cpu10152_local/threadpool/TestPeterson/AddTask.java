package com.example.cpu10152_local.threadpool.TestPeterson;

import android.util.Log;

import com.example.cpu10152_local.threadpool.Global;

/**
 * Created by cpu10152-local on 28/03/2018.
 */

public class AddTask  implements Runnable {

    private static final String TAG = "TAG";

    @Override
    public void run() {
        while (true){
            Global.sInteresse[0] = 1;
            Global.sTurn = 1;
            while (Global.sTurn == 1 && Global.sInteresse[1] == 1);
            Log.d(TAG, "Thread 1 In CS");
            Global.sCount++;
            Log.d(TAG, "Thread 1 Current Count: " + Global.sCount);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Global.sInteresse[0] = 0;
            Log.d(TAG, "Thread 1 Out CS");
        }

    }
}
