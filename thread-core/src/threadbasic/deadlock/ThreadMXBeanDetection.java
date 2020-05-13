package threadbasic.deadlock;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * 描述: 用ThreadMXBean检测死锁
 *
 * @author li
 * @date 2019/10/22
 */
public class ThreadMXBeanDetection implements Runnable{

    private int flag = 1;

    private static final Object OBJ1 = new Object();
    private static final Object OBJ2 = new Object();

    public static void main(String[] args) throws InterruptedException {
        ThreadMXBeanDetection r1 = new ThreadMXBeanDetection();
        ThreadMXBeanDetection r2 = new ThreadMXBeanDetection();
        r1.flag = 1;
        r2.flag = 0;
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
        Thread.sleep(1000);
        //得到 ThreadMXBean 实例
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        //得到发生死锁的线程数组
        long[] deadlockedThreads = threadMXBean.findDeadlockedThreads();
        int len = deadlockedThreads.length;
        if (len > 0){
            //遍历发生死锁的线程
            for (int i = 0; i < len; i++) {
                ThreadInfo threadInfo = threadMXBean.getThreadInfo(deadlockedThreads[i]);
                System.out.println("发现死锁 - ThreadName: " + threadInfo.getThreadName());
            }
        }
    }

    @Override
    public void run() {
        System.out.println("flag:" + flag);

        if (flag == 1){
            synchronized (OBJ1){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (OBJ2){
                    System.out.println("Thread1 gets two lock2.");
                }
            }
        }

        if (flag == 0){
            synchronized (OBJ2){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (OBJ1){
                    System.out.println("Thread2 gets two lock2.");
                }
            }
        }
    }
}
