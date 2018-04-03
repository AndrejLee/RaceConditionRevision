package com.example.cpu10152_local.threadpool.TestCountingSemaphore;

import android.util.Log;

/**
 * Created by cpu10152-local on 28/03/2018.
 */

public class Book {
    private static final String TAG = "TestCountingSemaphore";
    private String bookName;
    public Book(String bookName) {
        this.bookName = bookName;
    }
    public void read() {
        Log.d(TAG, bookName + " is being read......");
        try {
            Thread.sleep(2000);
        }catch(InterruptedException e) {
            e.printStackTrace();
        }
    }
    public String getBookName() {
        return bookName;
    }
}
