package com.example.cpu10152_local.threadpool.TestThreadPool;

import android.util.Log;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by cpu10152-local on 27/03/2018.
 */

public class MyThreadPool {

    private static final String TAG = "TAG";
    private final int nThreads;
    private final PoolWorker[] threads;
    private final LinkedBlockingQueue queue;

    public MyThreadPool(int nThreads){
        this.nThreads = nThreads;
        threads = new PoolWorker[nThreads];
        queue = new LinkedBlockingQueue();

        Log.d(TAG, "Create thread pool");

        for (int i = 0; i < nThreads; i++){
            threads[i] = new PoolWorker();
            threads[i].start();
        }
    }

    public void execute(Runnable task){
        synchronized (queue){
            Log.d(TAG, "Add task: " + task.toString());
            queue.add(task);
            queue.notify();
        }
    }

    private class PoolWorker extends Thread {
        public void run(){
            Runnable task = null;

            while(true){
                synchronized (queue) {
                    while (queue.isEmpty()) {
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            Log.d(TAG, "An error occurred while queue is waiting: " + e.getMessage());
                        }

                    }
                    Log.d(TAG, "Get task from queue");
                    task = (Runnable) queue.poll();
                }

                try {
                    Log.d(TAG, "Process task");
                    task.run();
                } catch (RuntimeException e){
                    Log.d(TAG,"Thread pool is interrupted due to an issue: " + e.getMessage());
                }
            }
        }
    }
}
