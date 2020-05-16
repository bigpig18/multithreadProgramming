package juc.lock.reentrantlock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述: 演示公平和不公平两种情况
 *
 * @author liyun
 * @date 2020/5/15 13:32
 */
public class FairLock {

    public static void main(String[] args) {
        Printer printer = new Printer();
        Thread[] thread = new Thread[10];
        for (int i = 0; i < thread.length; i++) {
            thread[i] = new Thread(new Job(printer));
        }
        for (int i = 0; i < thread.length; i++) {
            thread[i].start();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Job implements Runnable{

    private Printer printer;

    Job(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " 开始打印");
        printer.printObj(new Object());
        System.out.println(Thread.currentThread().getName() + " 打印完毕");
    }
}

    class Printer{
        /**
         * 公平锁  new ReentrantLock(true)
         */
        private Lock queueLock = new ReentrantLock();

        public void printObj(Object document){
            queueLock.lock();
            try{
                int duration = new Random().nextInt(10) + 1;
                System.out.println(Thread.currentThread().getName() + "正在打印,需要: " + duration);
                Thread.sleep(duration * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                queueLock.unlock();
            }

            queueLock.lock();
            try{
                int duration = new Random().nextInt(10) + 1;
                System.out.println(Thread.currentThread().getName() + "正在打印,需要: " + duration);
                Thread.sleep(duration * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                queueLock.unlock();
            }
        }
    }

