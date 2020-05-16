package juc.lock.lock;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述: 使用tryLock来避免死锁
 *
 * @author liyun
 * @date 2020/5/15 11:01
 */
public class TryLockDeadLock implements Runnable{

    int flag = 1;

    static Lock lock1 = new ReentrantLock();
    static Lock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        TryLockDeadLock try1 = new TryLockDeadLock();
        TryLockDeadLock try2 = new TryLockDeadLock();
        try1.flag = 1;
        try2.flag = 0;
        Thread thread1 = new Thread(try1);
        Thread thread2 = new Thread(try2);
        thread1.start();
        thread2.start();
    }

    @Override
    public void run() {
        for (int i = 0;i < 100;i++) {
            if (flag == 1){
                try {
                    if (lock1.tryLock(800L, TimeUnit.MILLISECONDS)){
                        try{
                            System.out.println(Thread.currentThread().getName() + "获取到了锁1");
                            Thread.sleep(new Random().nextInt(1000));

                            if (lock2.tryLock(800L,TimeUnit.MILLISECONDS)){
                                try{
                                    System.out.println(Thread.currentThread().getName() + "获取到锁2");
                                    System.out.println("成功获取到两把锁");
                                    break;
                                }finally {
                                    lock2.unlock();
                                }
                            }else{
                                System.out.println(Thread.currentThread().getName() + " 获取锁2失败，已重试");
                            }
                        }finally {
                            lock1.unlock();
                            Thread.sleep(new Random().nextInt(1000));
                        }
                    }else{
                        System.out.println(Thread.currentThread().getName() + " 获取锁1失败，已重试");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (flag == 0){
                try {
                    if (lock2.tryLock(800L, TimeUnit.MILLISECONDS)){
                        try{
                            System.out.println(Thread.currentThread().getName() + "获取到了锁2");
                            Thread.sleep(new Random().nextInt(1000));

                            if (lock1.tryLock(800L,TimeUnit.MILLISECONDS)){
                                try{
                                    System.out.println(Thread.currentThread().getName() + "获取到锁1");
                                    System.out.println("成功获取到两把锁");
                                    break;
                                }finally {
                                    lock2.unlock();
                                }
                            }else{
                                System.out.println(Thread.currentThread().getName() + " 获取锁1失败，已重试");
                            }
                        }finally {
                            lock1.unlock();
                            Thread.sleep(new Random().nextInt(1000));
                        }
                    }else{
                        System.out.println(Thread.currentThread().getName() + " 获取锁2失败，已重试");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
