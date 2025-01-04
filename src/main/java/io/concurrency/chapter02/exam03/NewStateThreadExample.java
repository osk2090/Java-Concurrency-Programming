package io.concurrency.chapter02.exam03;

public class NewStateThreadExample {
    public static void main(String[] args) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("스레드 실행 중");
            }
        });

        // thread 변수를 통해 start하지 않아서 state 값은 new로 출력
        System.out.println("스레드 상태: " + thread.getState());
    }
}
