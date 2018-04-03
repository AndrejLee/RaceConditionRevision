package com.example.cpu10152_local.threadpool.TestMonitor;

import android.util.Log;

/**
 * Created by cpu10152-local on 02/04/2018.
 */

public class PCbuffer {
    private static final String TAG = "TestMonitor";
    private Object buffer[];     /* Shared memory    */
    private int N ;              /* Buffer capacity  */
    private int count, in, out;  /* nb of elements, pointers */
    private WaitQueue notfull, notempty; /* wait queues */
    private MySemaphore mutex, urgent;     /* MySemaphores */
    private int urcount;         /* nb of threads waiting on
urgent */
    public PCbuffer(int argSize)
    {
        N = argSize;
        buffer = new Object[N];
        count = 0; in = 0; out = 0; urcount = 0;
        notfull = new WaitQueue(); notempty = new WaitQueue();
        mutex = new MySemaphore(1); urgent = new MySemaphore(0);
    }

    public  void append(Object data) throws InterruptedException {
        // Acquire mutex to grand permission to write data
        mutex.semWait();
        if (count == N) {
            // if buffer is full
            Log.d(TAG, "Buffer is full...");
            if (urcount > 0)
                // If there are unprocessed producers, put thread to queue, up urgent to notify to consumers
                notfull.qWait(urgent);
            else
                // If not, put thread to queue and release mutex
                notfull.qWait(mutex);
        }
        // Write data to buffer
        buffer[in] = data;
        in = (in + 1) % N; count++;
        Log.d(TAG, "Add object: " + data.toString());
        Thread.sleep(3000);
        // Increase the number of threads need to be processed
        urcount++;
        // Signal to queue to free a thread to consume, acquire urgent to prioritise process consumer first
        notempty.qSignal(urgent);

        urcount--;
        if (urcount > 0)
            // if there still is thread in queue, notify to process it
            urgent.semSignal();
        else
            // Release mutex
            mutex.semSignal();
    }

    public  Object take() throws InterruptedException {
        Object data;
        // Acquire mutex to read data
        mutex.semWait();
        if (count == 0) {
            Log.d(TAG, "Buffer is empty");
            if (urcount > 0)
                // if buffer is empty and there is a thread wants to consume, put it into queue and use urgent to notify producers
                notempty.qWait(urgent);
            else
                // if there is no consumers, release mutex
                notempty.qWait(mutex);
        }
        // Read data
        data = buffer[out];
        out = (out + 1) % N;
        count--;
        Log.d(TAG, "Remove object: " + data.toString());
        Thread.sleep(3000);
        // Increase the number of threads need to be processed
        urcount++;
        // Signal to queue to free a thread to produce, acquire urgent to prioritise producer first
        notfull.qSignal(urgent);
        urcount--;
        if (urcount > 0)
            // if there still is thread in queue, notify to process it
            urgent.semSignal();
        else
            // Release mutex
            mutex.semSignal();
        return data;
    }
}
