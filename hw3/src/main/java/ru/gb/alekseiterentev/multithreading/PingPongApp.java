package ru.gb.alekseiterentev.multithreading;

public class PingPongApp {

    private static final String PING = "ping";
    private static final String PONG = "pong";
    private static String currentMessage = PING;

    private final static Object monitor = new Object();

    public static void main(String[] args) {
        PingPongApp pingPongAppApp = new PingPongApp();
        new Thread(() -> {
            pingPongAppApp.ping();
        }).start();
        new Thread(() -> {
            pingPongAppApp.pong();
        }).start();
    }

    public static void ping() {
        synchronized (monitor) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (!PING.equals(currentMessage)) {
                        monitor.wait();
                    }
                    System.out.println(PING);
                    currentMessage = PONG;
                    monitor.notifyAll();
                    Thread.sleep(1500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void pong() {
        synchronized (monitor) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (!PONG.equals(currentMessage)) {
                        monitor.wait();
                    }
                    System.out.println(PONG);
                    currentMessage = PING;
                    monitor.notifyAll();
                    Thread.sleep(1500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
