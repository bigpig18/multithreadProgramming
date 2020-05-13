package juc.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 描述: 演示 newScheduledThreadPool
 *
 * @author liyun
 * @date 2020/5/13 13:22
 */
public class ScheduledThreadPool {

    public static void main(String[] args) {
        // 支持定时以及周期性任务的执行
        ScheduledExecutorService threadPool = Executors.newScheduledThreadPool(10);
        // delay 延迟时间 等5秒钟再运行
//        threadPool.schedule(new Task(),5, TimeUnit.SECONDS);
        // 以一定频率重复运行的 最开始的一次 等1秒钟运行，每隔3秒钟运行一次
        threadPool.scheduleAtFixedRate(new Task(),1,3,TimeUnit.SECONDS);
    }
}
