package juc.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 描述: 演示 newCachedThreadPool
 *
 * @author liyun
 * @date 2020/5/13 13:18
 */
public class CachedThreadPool {

    public static void main(String[] args) {
        // 无限线程
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++) {
            executorService.execute(new Task());
        }
    }
}
