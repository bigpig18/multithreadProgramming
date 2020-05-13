package juc.threadpool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 描述: 每一个任务对应一个线程，与线程池作比较
 *
 * @author liyun
 * @date 2020/5/13 11:30
 */
public class EveryTaskOneThread {

    public static void main(String[] args) {
        Thread thread = new Thread(new Task());
        thread.start();

        LinkedBlockingQueue<Integer> link = new LinkedBlockingQueue<>(10);
    }

    static class Task implements Runnable{

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " 开始工作...");
        }
    }
}
