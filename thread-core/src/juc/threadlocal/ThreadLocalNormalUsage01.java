package juc.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 描述: ThreadLocal使用场景1 ： 每个线程有一个独享的对象
 *  两个线程打印时间
 *
 * @author liyun
 * @date 2020/5/14 12:03
 */
public class ThreadLocalNormalUsage01 {

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("Time: " + new ThreadLocalNormalUsage01().date(10));
        }).start();

        new Thread(() -> {
            System.out.println("Time: " + new ThreadLocalNormalUsage01().date(111007));
        }).start();
    }

    private String date(int seconds){
        // 参数的单位是毫秒，从 1970.1.1 00:00:00 GMT 计时
        Date date = new Date(1000 * seconds);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }
}
