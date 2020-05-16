package juc.lock.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述: 可重入演示
 *
 * @author liyun
 * @date 2020/5/15 12:28
 */
public class GeiHoldCount {

    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        System.out.println(lock.getHoldCount());
        lock.lock();
        System.out.println(lock.getHoldCount());
        lock.lock();
        System.out.println(lock.getHoldCount());
        lock.lock();
        System.out.println(lock.getHoldCount());
        lock.unlock();
        lock.unlock();
        lock.unlock();
        System.out.println(lock.getHoldCount());
    }
}
