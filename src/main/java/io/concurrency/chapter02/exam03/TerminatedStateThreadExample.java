package io.concurrency.chapter02.exam03;

public class TerminatedStateThreadExample {
    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("스레드 실행 중");
            }
        });

        thread.start();
        thread.join(); // 해당 스레드가 정상종료될때까지 대기
        System.out.println("스레드 상태: " + thread.getState());
    }
}
