package com.example.cpu10152_local.threadpool.TestBinarySemaphore;

import android.util.Log;

/**
 * Created by cpu10152-local on 28/03/2018.
 */

public class Job implements Runnable {
    private static final String TAG = "TestBinarySemaphore";
    private Printer printer;
    private String jobName;
    public Job(Printer printer, String jobName) {
        this.printer = printer;
        this.jobName = jobName;
    }
    @Override
    public void run() {
        Log.d(TAG,"Job sent to printer:"+ jobName);
        printer.print(jobName);
    }
}
