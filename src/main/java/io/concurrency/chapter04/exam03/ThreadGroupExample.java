package io.concurrency.chapter04.exam03;

public class ThreadGroupExample {
    public static void main(String[] args) {
        ThreadGroup mainThreadGroup = Thread.currentThread().getThreadGroup();
        ThreadGroup customThreadGroup = new ThreadGroup("Custom Thread Group");

        // hint: 별도의 스레드 그룹을 칭하지 않으면 현재 스레드인 main 스레드에 할당된다
        Thread defaultGroupThread = new Thread(new GroupRunnable(), "DefaultGroupThread");

        // hint: 별도 스레드그룹을 할당해주면 해당 스레드 그룹에 해당 스레드를 주입해준다.
        Thread mainGroupThread = new Thread(mainThreadGroup, new GroupRunnable(), "MainGroupThread");
        Thread customGroupThread = new Thread(customThreadGroup, new GroupRunnable(), "CustomGroupThread");

        defaultGroupThread.start();
        mainGroupThread.start();
        customGroupThread.start();
    }

    static class GroupRunnable implements Runnable {

        @Override
        public void run() {
            Thread currentThread = Thread.currentThread();
            System.out.println(currentThread.getName() + " 는 " + currentThread.getThreadGroup().getName() + " 에 속한다.");
        }
    }
}
