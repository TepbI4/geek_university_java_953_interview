package ru.gb.alekseiterentev.multithreading;

public class ConcurrentCounterApp {

    public static void main(String[] args) {
        ConcurrentCounter counter = new ConcurrentCounter();

        Thread thread1 = new MyThread(counter);
        Thread thread2 = new MyThread(counter);

        thread1.start();
        thread2.start();
    }
}
