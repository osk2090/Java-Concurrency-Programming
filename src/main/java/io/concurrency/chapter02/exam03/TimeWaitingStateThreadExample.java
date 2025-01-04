package io.concurrency.chapter02.exam03;

public class TimeWaitingStateThreadExample {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        thread.start();
        Thread.sleep(100);
        System.out.println("스레드 상태: " + thread.getState());
    }
}
