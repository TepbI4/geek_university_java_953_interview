package ru.gb.alekseiterentev.multithreading;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentCounter {

    private int value;
    Lock lock = new ReentrantLock();

    public void count() {
        try {
            lock.lock();
            value++;
            Thread.sleep(2000);
            System.out.println(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
