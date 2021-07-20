package ru.gb.lesson3;

public class CounterTest {
    public static void main(String[] args) {
        Counter counter = new Counter();

        for (int i = 0; i < 100; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    counter.increase();
                }
            });
            thread.start();
        }
        System.out.println(counter.getCounter());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(counter.getCounter());
    }
}
