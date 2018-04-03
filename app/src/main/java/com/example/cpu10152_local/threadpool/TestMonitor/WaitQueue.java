package com.example.cpu10152_local.threadpool.TestMonitor;

import java.util.concurrent.Semaphore;

/**
 * Created by cpu10152-local on 02/04/2018.
 */

public class WaitQueue {

    private static final String TAG = "TestMonitor";
    private int qcount = 0;  /* the number of waiting threads */
    private MySemaphore Qsem;

    public WaitQueue()
    {
        Qsem = new MySemaphore(0);
/* creating a semaphore initialized to 0 */
    }

    public void qWait(MySemaphore Mutsem) throws InterruptedException
/* wait operation */
    {
        // Increase number of waiting threads
        qcount++;
        // Release mutex associated with the method calling qWait
        Mutsem.semSignal();
        // Down queue's semaphore, wait the thread
        Qsem.semWait();
        // if queue's semaphore is notified by qSignal function, release thread to continue processing, decrease the number of waiting threads
        qcount--;
    }

    public void qSignal(MySemaphore Mutsem) throws InterruptedException
/* signal operation  */
    {
        // Signal to waiting queue
        if (qcount > 0) {
            // if there are threads in queue, up queue's semaphore to notify threads
            Qsem.semSignal();
            // Acquire mutex associated with the method calling qSignal
            Mutsem.semWait();
        }
    }

    public boolean  qNonempty()
    {
        return qcount == 0;   /* testing if the queue is nonempty */
    }
}
