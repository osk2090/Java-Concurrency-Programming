package io.concurrency.chapter04.exam03;

public class ThreadGroupScopeExample {
    // hint: 우선순위 1이 제일 낮음 10이 제일 높음
    public static void main(String[] args) throws InterruptedException {
        ThreadGroup topGroup = new ThreadGroup("회상위 스레드 그룹");
        ThreadGroup subGroup = new ThreadGroup(topGroup, "하위 스레드 그룹");

        Thread topGroupThread = new Thread(topGroup, () -> {
            // 10
            System.out.println("상위 그룹 스레드에서 하위 그룹의 최대 우선 순위 설정 변경 전 : " + subGroup.getMaxPriority());
            subGroup.setMaxPriority(7); // hint: 결국엔 서브스레드 그룹은 탑스레드 그룹보다 높은 우선순위를 가질수 없다
            // 7
            System.out.println("상위 그룹 스레드에서 하위 그룹의 최대 우선 순위 설정 변경 후 : " + subGroup.getMaxPriority());
        }, "상위 스레드 그룹");

        Thread subGroupThread = new Thread(subGroup, () -> {
            // 10
            System.out.println("하위 그룹 스레드에서 상위 그룹의 최대 우선 순위 설정 변경 전 : " + topGroup.getMaxPriority());
            topGroup.setMaxPriority(8);
            // 4
            System.out.println("하위 그룹 스레드에서 상위 그룹의 최대 우선 순위 설정 변경 후 : " + topGroup.getMaxPriority());
        }, "하위 스레드 그룹");

        topGroupThread.start();
        subGroupThread.start();

        topGroupThread.join();
        subGroupThread.join();

        System.out.println(topGroupThread.getName() + " : " + topGroupThread.getPriority());
        System.out.println(subGroupThread.getName() + " : " + subGroupThread.getPriority());

        Thread userThread1 = new Thread(topGroup, () -> {
        }, "유저스레드 1");
        Thread userThread2 = new Thread(subGroup, () -> {
        }, "유저스레드 2");

        // hint: 만약 유저스레드의 우선순위를 바꾸고싶다면 그룹의 우선순위보다 낮아야된다 그보다 높게는 설정이 안된다.
        // hint: 예를들어 스레드 그룹의 우선순위가 7이면 하위 스레드 그룹 또는 스레드는 7 이하의 우선순위를 가지고 8이라는 우선순위는 선언할수 없다.
        // userThread1.setPriority(1);

        userThread1.start();
        userThread2.start();

        userThread1.join();
        userThread2.join();

        System.out.println(userThread1.getName() + " : " + userThread1.getPriority());
        System.out.println(userThread2.getName() + " : " + userThread2.getPriority());
    }
}
