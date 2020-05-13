package juc.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 描述: 内存溢出演示
 *  VM Option 中 内存设置的小一点  -Xmx8m -Xms8m
 *
 * @author liyun
 * @date 2020/5/13 12:59
 */
public class FixedThreadPoolOOM {
    private static ExecutorService executorService = Executors.newFixedThreadPool(1);
    public static void main(String[] args) {

        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            executorService.execute(new OutOfMemory());
        }
    }
}

class OutOfMemory implements Runnable{
    @Override
    public void run() {
        try {
            TimeUnit.MINUTES.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}