package io.concurrency.chapter02.exam02;

public class SynchronizeTest {
    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new MyRunnable(i));

            thread.start();
        }

        System.out.println("메인 스레드 종료");
    }

    static class MyRunnable implements Runnable {

        private final int threadId;

        public MyRunnable(int threadId) {
            this.threadId = threadId;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " : 스레드 실행 중..");
            new Counter().increment(threadId);
        }
    }

    static class Counter {
        private int count = 0;

        private void increment(int threadId) {
            count++;
            System.out.println(threadId + " : " + count);
        }
    }
}
