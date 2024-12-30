package io.concurrency.chapter01.exam03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class IOBoundExample {
    public static void main(String[] args) {
        int numThreads = Runtime.getRuntime().availableProcessors() * 2;
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

        for (int i = 0; i < numThreads; i++) {
            executorService.submit(() -> {

                try {
                    // 여기서 파일를 읽어들이고
                    // 그러므로 이 구간은 중간에 io burst 구간
                    for (int j = 0; j < 5; j++) {
                        Files.readAllLines(Path.of("/Users/osk2090/Documents/Git/Java-Concurrency-Programming/src/main/java/io/concurrency/chapter01/exam03/sample.txt"));
                        System.out.println("스레드: " + Thread.currentThread().getName() + ", " + j);
                    }

                    // 여기서는 cpu에거 간단한 연산을 맡긴다.
                    // 그러므로 이 구간은 중간에 cpu burst 구간
                    int result = 0;
                    for (long j = 0; j < 10; j++) {
                        result += j;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        executorService.shutdown();
    }
}
