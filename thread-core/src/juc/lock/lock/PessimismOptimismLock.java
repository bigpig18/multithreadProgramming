package juc.lock.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 描述: 乐观锁  悲观锁
 *
 * @author liyun
 * @date 2020/5/15 12:01
 */
public class PessimismOptimismLock {

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.incrementAndGet();
    }

    public synchronized void testMethod(){

    }
}
