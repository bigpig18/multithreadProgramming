package threadbasic.jmm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 描述: volatile适用的情况1
 * 如果一个共享变量自始至终只被各个线程赋值，而没有其他操作，
 * 那么可以用 volatile 代替 synchronized 或 原子变量，因为赋值
 * 本身是又原子性的，volatile又保证了可见性，足以保证线程安全
 * @author li
 * @date 2019/10/17
 */
public class UseVolatile implements Runnable{

    volatile boolean done = false;

    AtomicInteger realA = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        UseVolatile r = new UseVolatile();
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("done: " + r.done);
        System.out.println("realA: " + r.realA);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            setDone();
            realA.incrementAndGet();
        }
    }

    private void setDone() {
        done = true;
    }
}
