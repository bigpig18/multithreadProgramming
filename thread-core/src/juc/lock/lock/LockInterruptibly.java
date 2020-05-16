package juc.lock.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述: 相当于tryLock(有参) 中将时间设置为无限大,
 * 在等待的过程中可以被中断
 *
 * @author liyun
 * @date 2020/5/15 11:21
 */
public class LockInterruptibly implements Runnable{

    private Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        LockInterruptibly interruptibly = new LockInterruptibly();
        Thread thread1 = new Thread(interruptibly);
        Thread thread2 = new Thread(interruptibly);
        thread1.start();
        thread2.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        thread1.interrupt();
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "尝试获取锁.");
        try {
            lock.lockInterruptibly();
            try{
                System.out.println(Thread.currentThread().getName() + "拿到了锁.");
                Thread.sleep(5000);
            }catch (InterruptedException e){
                System.out.println("睡眠期间被中断.");
            }finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + "释放了锁.");
            }
        } catch (InterruptedException e) {
            System.out.println("等锁期间被中断.");
        }
    }
}
