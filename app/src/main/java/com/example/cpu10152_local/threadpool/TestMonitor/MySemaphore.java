package com.example.cpu10152_local.threadpool.TestMonitor;

/**
 * Created by cpu10152-local on 02/04/2018.
 */

public class MySemaphore {
    private static final String TAG = "TestMonitor";
    private int value;               /* the value of the semaphore */
    private int nbWait = 0;          /* nb waiting */
    public MySemaphore(int inival)
    {
        value = inival;
    }

    public synchronized void semWait() throws InterruptedException {
        // if there is waiting threads in the queue.
        while (value <= 0)
        {
            // wait thread, increase number of waiting threads.
            nbWait++;
            wait();
        }
        // Down the value
        value--;
    }

    public synchronized void semSignal()
    {
        // Up the value
        value++;
        // if there is waiting threads in the queue.
        if (nbWait > 0)
        {
            // notify to wake up it, decrease number of waiting threads.
            nbWait--;
            notify();
        }
    }

    public synchronized int semNbWait()
    {
        return nbWait;
    }
}
