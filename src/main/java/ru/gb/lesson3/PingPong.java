package ru.gb.lesson3;

import java.util.concurrent.Semaphore;

public class PingPong {
    public static void main(String[] args) {

        Semaphore sPing = new Semaphore(1);
        Semaphore sPong = new Semaphore(0);

        Thread tPing = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        sPing.acquire();
                        System.out.println("Ping");
                        sPong.release();
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }
        });

        Thread tPong = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    try {
                        sPong.acquire();
                        System.out.println("Pong");
                        sPing.release();
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }
        });

        tPing.start();
        tPong.start();

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        tPing.interrupt();
        tPong.interrupt();
    }
}
