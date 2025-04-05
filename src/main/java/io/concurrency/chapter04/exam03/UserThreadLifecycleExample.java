package io.concurrency.chapter04.exam03;

public class UserThreadLifecycleExample {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                System.out.println("사용자 스레드 1 실행 중...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("사용자 스레드 1 종료");
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 2; i++) {
                System.out.println("사용자 스레드 2 실행 중...");
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println("사용자 스레드 2 종료");
        });

        thread1.start();
        thread2.start();

        // hint: 여기서 join을 하지않으면 사용자 스레드는 즉 main 스레드는 본인이 할것만 하고 죽을것이다
        // hint: 그래서 데몬스레드는 정상적인 종료를 하지 않는다. 그래서 join을 명시해야지 사용자 스레드는 데몬스레드의 동작을 보장해준다.
        thread1.join();
        thread2.join();

        System.out.println("모든 사용자 스레드가 종료되었습니다. 메인 스레드 종료");
    }
}
