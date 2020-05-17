package juc.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 描述: AtomicInteger基本用法 同时对比非原子类的线程安全问题
 *
 * @author liyun
 * @date 2020/5/17 12:42
 */
public class AtomicIntegerDemo implements Runnable{

    private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger();

    private static volatile int basicCount = 0;

    public void incrementAtomic(){
        ATOMIC_INTEGER.getAndIncrement();
//        ATOMIC_INTEGER.getAndAdd(10);
//        ATOMIC_INTEGER.getAndDecrement();
    }

    public void incrementBasic(){
        basicCount++;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            incrementAtomic();
            incrementBasic();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerDemo demo1 = new AtomicIntegerDemo();
        AtomicIntegerDemo demo2 = new AtomicIntegerDemo();

        Thread t1 = new Thread(demo1);
        Thread t2 = new Thread(demo2);

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Atomic: " + ATOMIC_INTEGER.get());
        System.out.println("Basic: " + basicCount);
    }
}
