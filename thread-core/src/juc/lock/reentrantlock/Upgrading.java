package juc.lock.reentrantlock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 描述: 锁的升降级
 *
 * @author liyun
 * @date 2020/5/16 11:03
 */
public class Upgrading {

    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock(false);

    private static ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();

    private static ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

    public static void main(String[] args) {
        new Thread(() -> writeDowngrading(),"Thread1").start();
        new Thread(() -> readUpgrading(),"Thread2").start();
    }

    private static void readUpgrading(){
        System.out.println(Thread.currentThread().getName() + " 开始尝试获取读锁");
        readLock.lock();
        try{
            System.out.println(Thread.currentThread().getName() + " 得到读锁，正在读取");
            Thread.sleep(20);
            System.out.println(Thread.currentThread().getName() + " 尝试读锁升级为写锁,但不会成功，会带来阻塞");
            writeLock.lock();
            System.out.println(Thread.currentThread().getName() + " 升级成功");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + " 释放读锁");
            readLock.unlock();
        }
    }

    private static void writeDowngrading(){
        System.out.println(Thread.currentThread().getName() + " 开始尝试获取写锁");
        writeLock.lock();
        try{
            System.out.println(Thread.currentThread().getName() + " 得到写锁，正在写入");
            Thread.sleep(40);
            readLock.lock();
            System.out.println(Thread.currentThread().getName() + " 在不释放写锁的情况下，直接获取读锁，降级");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
            System.out.println(Thread.currentThread().getName() + " 释放写锁");
            writeLock.unlock();
        }
    }
}
