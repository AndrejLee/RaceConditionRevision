package com.example.cpu10152_local.threadpool.TestCountingSemaphore;

/**
 * Created by cpu10152-local on 28/03/2018.
 */

public class Reader implements Runnable {
    private Library library;
    public Reader(Library library) {
        this.library = library;
    }
    @Override
    public void run() {
        try {
            Book book = (Book)library.issueBook();
            book.read();
            library.returnBook(book);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}