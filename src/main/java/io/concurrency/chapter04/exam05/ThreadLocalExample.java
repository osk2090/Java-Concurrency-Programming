package io.concurrency.chapter04.exam05;

public class ThreadLocalExample {

//  private static ThreadLocal<String> threadLocal = new ThreadLocal<>();
  private static ThreadLocal<String> threadLocal = ThreadLocal.withInitial(() -> "Hello World");

  public static void main(String[] args) {
    new Thread(() -> {
      System.out.println(Thread.currentThread().getName() + ": " + threadLocal.get());
      threadLocal.set("스레드 1의 값");
      System.out.println(Thread.currentThread().getName() + ": " + threadLocal.get());
      threadLocal.remove();
      System.out.println(Thread.currentThread().getName() + ": " + threadLocal.get()); // remove하고 다시 찍어보면 기본값이 들어가 있음
    },"Thread-1").start();

    new Thread(() -> {
      System.out.println(Thread.currentThread().getName() + ": " + threadLocal.get());
      threadLocal.set("스레드 2의 값");
      System.out.println(Thread.currentThread().getName() + ": " + threadLocal.get());
      threadLocal.remove();
      System.out.println(Thread.currentThread().getName() + ": " + threadLocal.get());
    },"Thread-2").start();
  }
}
