package io.concurrency.chapter04.exam03;

public class ThreadLocalExample {

  private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

  public static void main(String[] args) {
    new Thread(() -> {
      System.out.println(Thread.currentThread().getName() + ": " + threadLocal.get());
      threadLocal.set("스레드 1의 값");
      System.out.println(Thread.currentThread().getName() + ": " + threadLocal.get());
    },"Thread-1").start();

    new Thread(() -> {
      System.out.println(Thread.currentThread().getName() + ": " + threadLocal.get());
      threadLocal.set("스레드 2의 값");
      System.out.println(Thread.currentThread().getName() + ": " + threadLocal.get());
    },"Thread-2").start();


  }
}
