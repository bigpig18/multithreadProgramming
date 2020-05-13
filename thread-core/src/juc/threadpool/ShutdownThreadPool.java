package juc.threadpool;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 描述: 演示关闭线程池
 *
 * @author liyun
 * @date 2020/5/13 13:50
 */
public class ShutdownThreadPool {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 1000; i++) {
            threadPool.execute(new ShutDownTask());
        }
        Thread.sleep(1500);
        // 立即停止线程 会返回队列里的未执行的任务
        List<Runnable> runnables = threadPool.shutdownNow();

//        // awaitTermination() 检测线程池在3秒钟之后是否完全关闭了
//        boolean b = threadPool.awaitTermination(3L, TimeUnit.SECONDS);
//        System.out.println(b);
//        // isShutdown() 看线程是否停止运行 但是这个方法 开始停止了但线程池还没完全停止就返回true了
//        System.out.println(threadPool.isShutdown());
//        threadPool.shutdown();
//        System.out.println(threadPool.isShutdown());
//        // isTerminated() 线程真正是否停止
//        System.out.println(threadPool.isTerminated());
//        // 使用了 shutdown() ，提交新任务是提交不进去的
//        threadPool.execute(new ShutDownTask());
    }
}

class ShutDownTask implements Runnable{
    @Override
    public void run() {
        try {
            Thread.sleep(500);
            System.out.println(Thread.currentThread().getName() + " is working.");
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " is interrupted");
        }

    }
}
