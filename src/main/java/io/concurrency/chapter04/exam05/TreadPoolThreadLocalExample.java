package io.concurrency.chapter04.exam05;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TreadPoolThreadLocalExample {
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        // 2개의 스레드를 가진 스레드풀 생성
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // 첫번째 작업: threadLocal 값을 설정
        executor.submit(() -> {
            threadLocal.set("작업 1의 값");
            System.out.println(Thread.currentThread().getName() + " : " + threadLocal.get());
            threadLocal.remove(); // 초기화 해야지 새로운 값을 받을수 있다
        });


        // 잠시 대기
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 여러 번의 두번째 작업: ThreadLocal 값을 설정하지 않고 바로 값을 가져와 출력
        for (int i = 0; i < 5; i++) {
            executor.submit(() -> {
                System.out.println(Thread.currentThread().getName() + " : " + threadLocal.get());
            });
        }

        executor.shutdown();
    }
}
