package io.concurrency.chapter01.exam03;

import java.util.ArrayList;
import java.util.concurrent.*;

public class CPUBoundExample {
    public static void main(String[] args) {
        int numThreads = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

        long startTime2 = System.currentTimeMillis();
        ArrayList<Future<?>> futures = new ArrayList<>();

        for (int i = 0; i < numThreads; i++) {
            Future<?> future = executorService.submit(() -> {

                long result = 0;
                for (int j = 0; j < 1000000000L; j++) {
                    result += j;
                }

                try {
                    Thread.sleep(1);
                }catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                System.out.println("스레드: " + Thread.currentThread().getName() + ", " + result);
            });

            futures.add(future);
        }

        futures.forEach(f -> {
            try {
                f.get();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        });

        long endTime2 = System.currentTimeMillis();
        System.out.println("CPU 갯수를 초과하는 데이터를 병렬로 처리하는 데 걸린 시간: " + (endTime2 - startTime2));
        executorService.shutdown();
    }
}
