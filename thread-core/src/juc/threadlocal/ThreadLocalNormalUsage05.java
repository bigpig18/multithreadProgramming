package juc.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 描述: ThreadLocal使用场景1 ： 每个线程有一个独享的对象
 * 用ThreadLocal 给每个线程分配自己的SDF对象 保证线程安全的同时高效利用内存
 *
 * @author liyun
 * @date 2020/5/14 12:03
 */
public class ThreadLocalNormalUsage05 {

    public static ExecutorService threadPool = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            threadPool.submit(() -> System.out.println("Time: " + new ThreadLocalNormalUsage05().date(finalI)));
        }
        threadPool.shutdown();
    }

    private String date(int seconds){
        // 参数的单位是毫秒，从 1970.1.1 00:00:00 GMT 计时
        Date date = new Date(1000 * seconds);
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format = ThreadSafeFormatter.dateFormatThreadLocal.get();
        return format.format(date);
    }
}

class ThreadSafeFormatter{
    public static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
}
