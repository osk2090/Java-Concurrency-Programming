package io.concurrency.chapter04.exam02;

public class InterruptedExceptionThreadStopExample3 {
    public static void main(String[] args) {
        Thread worker = new Thread(() -> {
            try {
                // while문에서 일단 해당 스레드는 아직 인터럽트가 발생하지 않아 false를 리턴해서 while문이 동작할 것이다.
                // 근데 밑에 stopper에서 worker에 인터럽트를 걸면 while문에서는 !true=false가 되니 동작을 멈춘다.
                while (!Thread.interrupted()) {
                    System.out.println("작업 스레드가 실행 중입니다.");
                    System.out.println("인터럽트 상태 1: " + Thread.currentThread().isInterrupted());
                    Thread.sleep(500);
                }

            } catch (InterruptedException e) {
                System.out.println("인터럽트 상태 2 :" + Thread.currentThread().isInterrupted());
                Thread.currentThread().interrupt();
            }

            System.out.println("작업 스레드가 중단되었습니다.");
            System.out.println("인터럽트 상태 3: " + Thread.currentThread().isInterrupted());
        });

        Thread stopper = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            worker.interrupt();
            System.out.println("중단 스레드가 작업 스레드를 중단시켰습니다.");
        });

        worker.start();
        stopper.start();
    }
}
