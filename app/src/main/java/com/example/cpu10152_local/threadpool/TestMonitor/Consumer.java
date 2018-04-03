package com.example.cpu10152_local.threadpool.TestMonitor;

import android.content.ContentProvider;
import android.util.Log;

/**
 * Created by cpu10152-local on 02/04/2018.
 */

public class Consumer implements Runnable {

    private static final String TAG = "TestMonitor";
    private PCbuffer mBuffer;

    public Consumer (PCbuffer buffer){
        this.mBuffer = buffer;
    }

    @Override
    public void run() {
        while (true){
            Object data = null;
            try {
                data = mBuffer.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.d(TAG, "Consume Object: " + data.toString());
        }
    }
}
