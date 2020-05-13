package juc.threadpool;

import threadbasic.createthread.wrongways.ThreadPool;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述: 演示每个任务执行前后可放钩子函数 可暂停的线程池
 *
 * @author liyun
 * @date 2020/5/13 14:37
 */
public class PauseThreadPool extends ThreadPoolExecutor {

    private boolean isPaused;

    private final ReentrantLock lock = new ReentrantLock();

    private Condition unpaused = lock.newCondition();

    private PauseThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        lock.lock();
            try {
                while (isPaused) {
                    unpaused.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
    }

    private void pause(){
        lock.lock();
        try {
            isPaused = true;
            System.out.println("ThreadPool Is Paused");
        } finally {
            lock.unlock();
        }
    }

    public void resume(){
        lock.lock();
        try{
            isPaused = false;
            unpaused.signalAll();
            System.out.println("ThreadPool Is Resumed");
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPoolExecutor = new ThreadPoolExecutor(10, 10, 10L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));
        PauseThreadPool pauseThreadPool = new PauseThreadPool(10, 20, 10L, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        Runnable runnable = () -> {
            System.out.println("Thread is working.");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        for (int i = 0; i < 10000; i++) {
            pauseThreadPool.execute(runnable);
        }
        Thread.sleep(1500);
        pauseThreadPool.pause();
        Thread.sleep(1500);
        pauseThreadPool.resume();
    }
}
