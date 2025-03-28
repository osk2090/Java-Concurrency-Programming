package io.concurrency.chapter03.exam04;

public class ThreadAliveExample {
    public static void main(String[] args) {
        Thread task1 = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("작업 스레드 1 실행 중...");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread task2 = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println("작업 스레드 2 실행 중...");
                try {
                    Thread.sleep(150);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        task1.start();
        task2.start();

        while (task1.isAlive() || task2.isAlive()) {
            System.out.println("작업 스레드 1 상태:  " + task1.isAlive());
            System.out.println("작업 스레드 2 상태:  " + task2.isAlive());
            try {
                Thread.sleep(100000000000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println("모든 스레드가 완료되었습니다.");
    }
}
