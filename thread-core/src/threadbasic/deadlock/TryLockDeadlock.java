package threadbasic.deadlock;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述: 用tryLock来避免死锁
 *
 * @author li
 * @date 2019/10/23
 */
public class TryLockDeadlock implements Runnable{

    int flag = 1;
    static Lock lock1 = new ReentrantLock();
    static Lock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        TryLockDeadlock r1 = new TryLockDeadlock();
        TryLockDeadlock r2 = new TryLockDeadlock();
        r1.flag = 1;
        r2.flag = 0;
        new Thread(r1).start();
        new Thread(r2).start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            if (flag == 1){
                try {
                    if (lock1.tryLock(800, TimeUnit.MILLISECONDS)){
                        System.out.println("线程1获取到lock1 ...");
                        Thread.sleep(new Random().nextInt(1000));
                        if (lock2.tryLock(800,TimeUnit.MILLISECONDS)){
                            System.out.println("线程1获取到lock2 ...");
                            System.out.println("线程1拿到两把锁 ...");
                            lock2.unlock();
                            lock1.unlock();
                            break;
                        }else{
                            System.out.println("线程1尝试获取lock2失败 ...");
                            lock1.unlock();
                            Thread.sleep(new Random().nextInt(1000));
                        }
                    }else{
                        System.out.println("线程1未拿到lock1 ...");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (flag == 0){
                try {
                    if (lock2.tryLock(3000, TimeUnit.MILLISECONDS)){
                        System.out.println("线程2获取到lock2 ...");
                        Thread.sleep(new Random().nextInt(1000));
                        if (lock1.tryLock(3000,TimeUnit.MILLISECONDS)){
                            System.out.println("线程2获取到lock1 ...");
                            System.out.println("线程2拿到两把锁 ...");
                            lock2.unlock();
                            lock1.unlock();
                            break;
                        }else{
                            System.out.println("线程2尝试获取lock1失败 ...");
                            lock1.unlock();
                            Thread.sleep(new Random().nextInt(1000));
                        }
                    }else{
                        System.out.println("线程2未拿到lock2 ...");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
