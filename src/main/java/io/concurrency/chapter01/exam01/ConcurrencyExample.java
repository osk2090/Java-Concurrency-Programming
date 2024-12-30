package io.concurrency.chapter01.exam01;

import java.util.ArrayList;

public class ConcurrencyExample {
    public static void main(String[] args) {
//        int cpuCores = Runtime.getRuntime().availableProcessors() * 2;
        int cpuCores = 11;

        ArrayList<Integer> data = new ArrayList<>();
        for(int i = 0; i < cpuCores; i++) {
            data.add(i);
        }

        long startTime1 = System.currentTimeMillis();
        long sum1 = data.parallelStream().mapToLong(i -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return i + i;
        }).sum();

        long endTime1 = System.currentTimeMillis();
        System.out.println("CPU 갯수만큼 데이터를 병렬로 처리하는 데 걸린 시간: " + (endTime1 - startTime1) + " ms");
        System.out.println("결과1: " + sum1);
    }
}
