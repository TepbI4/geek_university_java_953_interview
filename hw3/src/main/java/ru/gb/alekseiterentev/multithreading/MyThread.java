package ru.gb.alekseiterentev.multithreading;

public class MyThread extends Thread {

    private ConcurrentCounter counter;

    public MyThread(ConcurrentCounter counter) {
        super();
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            counter.count();
        }
    }
}
