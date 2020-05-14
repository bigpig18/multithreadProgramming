package juc.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 描述: ThreadLocal使用场景1 ： 每个线程有一个独享的对象
 * 加锁解决线程安全问题
 *
 * @author liyun
 * @date 2020/5/14 12:03
 */
public class ThreadLocalNormalUsage04 {

    public static ExecutorService threadPool = Executors.newFixedThreadPool(10);

    static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            threadPool.submit(() -> System.out.println("Time: " + new ThreadLocalNormalUsage04().date(finalI)));
        }
        threadPool.shutdown();
    }

    private String date(int seconds){
        // 参数的单位是毫秒，从 1970.1.1 00:00:00 GMT 计时
        Date date;
        date = new Date(1000 * seconds);
        String s;
        synchronized (ThreadLocalNormalUsage04.class) {
            s = format.format(date);
        }
        return s;
    }
}
