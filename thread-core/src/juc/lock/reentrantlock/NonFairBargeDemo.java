package juc.lock.reentrantlock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 描述: 演示非公平和公平的ReentrantReadWriteLock的策略
 *
 * @author liyun
 * @date 2020/5/16 11:03
 */
public class NonFairBargeDemo {

    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock(false);

    private static ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();

    private static ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

    public static void main(String[] args) {
        new Thread(() -> write(),"Thread1").start();
        new Thread(() -> read(),"Thread2").start();
        new Thread(() -> read(),"Thread3").start();
        new Thread(() -> write(),"Thread4").start();
        new Thread(() -> read(),"Thread5").start();
        new Thread(() -> {
            Thread[] threads = new Thread[1000];
            for (int i = 0; i < threads.length; i++) {
                threads[i] = new Thread(() -> read(),"子线程创建的Thread-" + i);
            }
            for (int i = 0; i < threads.length; i++) {
                threads[i].start();
            }
        }).start();
    }

    private static void read(){
        System.out.println(Thread.currentThread().getName() + " 开始尝试获取读锁");
        readLock.lock();
        try{
            System.out.println(Thread.currentThread().getName() + " 得到读锁，正在读取");
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + " 释放读锁");
            readLock.unlock();
        }
    }

    private static void write(){
        System.out.println(Thread.currentThread().getName() + " 开始尝试获取写锁");
        writeLock.lock();
        try{
            System.out.println(Thread.currentThread().getName() + " 得到写锁，正在写入");
            Thread.sleep(40);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + " 释放写锁");
            writeLock.unlock();
        }
    }
}
