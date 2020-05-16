package juc.lock.reentrantlock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 描述: 读写锁升级购票
 *
 * @author liyun
 * @date 2020/5/15 14:06
 */
public class CinemaReadWriter {

    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

    private static ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();

    private static ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

    public static void main(String[] args) {
        new Thread(() -> read(),"Thread1").start();
        new Thread(() -> read(),"Thread2").start();
        new Thread(() -> write(),"Thread3").start();
        new Thread(() -> write(),"Thread4").start();
    }

    private static void read(){
        readLock.lock();
        try{
            System.out.println(Thread.currentThread().getName() + " 得到读锁，正在读取中");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放了读锁");
            readLock.unlock();
        }
    }

    private static void write(){
        writeLock.lock();
        try{
            System.out.println(Thread.currentThread().getName() + " 得到写锁，正在写入");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放了写锁");
            writeLock.unlock();
        }
    }
}
