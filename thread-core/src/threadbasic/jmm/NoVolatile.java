package threadbasic.jmm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 描述: 不适用volatile关键字的场景
 *  1.多线程场景下 a++ 有可能会结果出错 使用volatile 是解决不来的
 * @author li
 * @date 2019/10/17
 */
public class NoVolatile implements Runnable {

    volatile int a = 0;

    AtomicInteger realA = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        NoVolatile r = new NoVolatile();
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("a: " + r.a);
        System.out.println("realA: " + r.realA);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            a++;
            realA.incrementAndGet();
        }
    }
}
