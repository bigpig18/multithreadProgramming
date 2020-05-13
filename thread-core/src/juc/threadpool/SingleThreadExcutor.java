package juc.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 描述: 演示 newSingleThreadExecutor
 *
 * @author liyun
 * @date 2020/5/13 13:08
 */
public class SingleThreadExcutor {

    public static void main(String[] args) {
        // newSingleThreadExecutor 只有一个线程
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        for (int i = 0; i < 100; i++) {
            executorService.execute(new Task());
        }
    }
}
