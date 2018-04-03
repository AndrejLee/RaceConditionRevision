package com.example.cpu10152_local.threadpool;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.cpu10152_local.threadpool.TestBinarySemaphore.Job;
import com.example.cpu10152_local.threadpool.TestBinarySemaphore.Printer;
import com.example.cpu10152_local.threadpool.TestCountingSemaphore.Library;
import com.example.cpu10152_local.threadpool.TestCountingSemaphore.Reader;
import com.example.cpu10152_local.threadpool.TestMonitor.Consumer;
import com.example.cpu10152_local.threadpool.TestMonitor.PCbuffer;
import com.example.cpu10152_local.threadpool.TestMonitor.Producer;
import com.example.cpu10152_local.threadpool.TestPeterson.AddTask;
import com.example.cpu10152_local.threadpool.TestPeterson.AddTask1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Manual implement thread pool
//        MyThreadPool threadPool = new MyThreadPool(2);
//        for (int i = 0; i < 10; i++){
//            Task task = new Task(i);
//            threadPool.execute(task);
//        }

        // ExecutorService thread pool
//        ExecutorService pool = Executors.newFixedThreadPool(2);
//        ExecutorService pool = Executors.newScheduledThreadPool(2);
//        ExecutorService pool = Executors.newCachedThreadPool();
//
//        for (int i = 0; i < 10; i++){
//            Task task = new Task(i);
//            pool.execute(task);
//        }
//        pool.shutdown();

        // ThreadPoolExecutor

        //RejectedExecutionHandler implementation
//        RejectedExecutionHandler rejectionHandler = new RejectedExecutionHandler() {
//            @Override
//            public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
//                Log.d(TAG,runnable.toString() + " is rejected");
//            }
//        };
        //Get the ThreadFactory implementation to use
//        ThreadFactory threadFactory = Executors.defaultThreadFactory();
//        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2, 4, 10, TimeUnit.SECONDS,
//                new ArrayBlockingQueue<Runnable>(2), threadFactory, rejectionHandler);
//
//        for (int i = 0; i < 10; i++){
//            Task task = new Task(i);
//            poolExecutor.execute(task);
//        }
//        poolExecutor.shutdown();
//        poolExecutor.execute(new Task(11));

//        Test Peterson's algorithm
//        Thread thread1 = new Thread(new AddTask());
//        Thread thread2 = new Thread(new AddTask1());
//        thread1.start();
//        thread2.start();


        // Test Counting Semaphore
//        final int threadCount = 6;
//        final ExecutorService exService = Executors.newFixedThreadPool(threadCount);
//        final Library library = new Library();
//        for(int i=0; i < threadCount; i++) {
//            Reader reader = new Reader(library);
//            exService.execute(reader);
//        }
//        exService.shutdown();

        // Test Binary Semaphore
//        final int threadCount = 5;
//        final ExecutorService exService = Executors.newFixedThreadPool(threadCount);
//        final Printer printer = new Printer();
//        for (int i=1; i<= threadCount; i++) {
//            exService.execute(new Job(printer, "Job-"+i));
//        }
//        exService.shutdown();

        // Test Monitor
        PCbuffer buffer = new PCbuffer(5);
        Thread thread1 = new Thread(new Producer(buffer));
        Thread thread2 = new Thread(new Consumer(buffer));
        thread1.start();
        thread2.start();
    }
}
