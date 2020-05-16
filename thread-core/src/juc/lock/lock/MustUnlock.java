package juc.lock.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述: Lock 不会像syn 一样异常的时候自动释放锁，
 *  所以要在 finally 中手动释放锁，以保证发生异常
 *  的时候锁一定释放
 *
 * @author liyun
 * @date 2020/5/15 10:51
 */
public class MustUnlock {

    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        lock.lock();
        try{
            //获取本所保护的资源
            System.out.println(Thread.currentThread().getName() + " is working.");
        }finally {
            lock.unlock();
        }
    }
}
