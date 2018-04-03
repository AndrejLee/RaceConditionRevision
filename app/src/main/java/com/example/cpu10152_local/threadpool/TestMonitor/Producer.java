package com.example.cpu10152_local.threadpool.TestMonitor;

import android.util.Log;
import android.widget.ProgressBar;

/**
 * Created by cpu10152-local on 02/04/2018.
 */

public class Producer implements Runnable {

    private static final String TAG = "TestMonitor";
    private PCbuffer mBuffer;
    private int i;

    public Producer (PCbuffer buffer){
        this.mBuffer = buffer;
        i = 0;
    }

    @Override
    public void run() {
        while (true){
            Log.d(TAG, "Produce Object...");
            try {
                mBuffer.append("Object " + i++);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
