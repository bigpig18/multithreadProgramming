package juc.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 描述:  演示 newFixedThreadPool
 *
 * @author liyun
 * @date 2020/5/13 12:53
 */
public class FixedThreadPoolTest {

    public static void main(String[] args) {
        // newFixedThreadPool 核心线程池数量跟最大线程池数量一样
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 1000; i++) {
            executorService.execute(new Task());
        }
    }
}

class Task implements Runnable{
    @Override
    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " 执行任务.");
    }
}
