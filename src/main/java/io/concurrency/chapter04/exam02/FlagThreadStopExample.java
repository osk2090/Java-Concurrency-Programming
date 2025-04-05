package io.concurrency.chapter04.exam02;

public class FlagThreadStopExample {
//    private volatile static boolean running = true; // 스레드마다 한개의 필드를 바라보게 하여 공유될수 있도록 하는 volatile
    private static boolean running = true;

    public static void main(String[] args) {
        new Thread(() -> {
            int count = 0;

            while (running) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                count++;
            }
            System.out.println("스레드 1 종료, count: " + count);
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }

            System.out.println("스레드 2 종료");
            running = false;
        }).start();
    }
}
