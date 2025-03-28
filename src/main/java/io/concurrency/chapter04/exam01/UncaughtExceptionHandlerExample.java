package io.concurrency.chapter04.exam01;

public class UncaughtExceptionHandlerExample {
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            System.out.println("스레드 시작!");

            // 예기치 않은 예외 발생
            throw new RuntimeException("스레드 예외 발생");
        });

        thread1.setUncaughtExceptionHandler((t, e) -> {
            System.out.println(t.getName() + " 에서 예외 발생 " + e);
        });

        thread1.start();


        Thread thread2 = new Thread(() -> {
            System.out.println("스레드 시작!");

            // 예기치 않은 예외 발생
            throw new RuntimeException("스레드 예외 발생");
        });

        thread2.setUncaughtExceptionHandler((t, e) -> {
            System.out.println(t.getName() + " 에서 예외 발생 " + e);
        });

        thread2.start();
    }
}
