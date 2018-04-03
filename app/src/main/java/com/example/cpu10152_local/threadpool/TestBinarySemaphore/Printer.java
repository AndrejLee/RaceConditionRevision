package com.example.cpu10152_local.threadpool.TestBinarySemaphore;

import android.util.Log;

import java.util.concurrent.Semaphore;

/**
 * Created by cpu10152-local on 28/03/2018.
 */

public class Printer {
    private static final int MAX_PERMIT = 1;
    private static final String TAG = "TestBinarySemaphore";
    private final Semaphore semaphore = new Semaphore(MAX_PERMIT, true);
    public void print(String jobName) {
        try {
            semaphore.acquire();
            Log.d(TAG,"Printing Job: "+ jobName);
            Thread.sleep(2000);
            Log.d(TAG,"Finished Job: "+ jobName);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            semaphore.release();
        }
    }
}
