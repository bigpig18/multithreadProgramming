package createthread.threadobjectclasscommonmethods;

/**
 * 两个线程交替打印0~100奇偶数(wait/notify 实现)
 * @author li
 * @date 2019/9/25
 */
public class WaitNotifyPrintOddEvenWait {
    private static int count;
    private static Object lock = new Object();

    //新建两个线程，分别打印奇偶数
    //拿到锁，就打印
    //打印完，唤醒其他线程，自己休眠

    public static void main(String[] args) throws InterruptedException {
        new Thread(new TurningRunner(),"偶数").start();
        Thread.sleep(100);
        new Thread(new TurningRunner(),"奇数").start();
    }

    static class TurningRunner implements Runnable{
        @Override
        public void run() {
            while(count < 100){
                synchronized (lock){
                    System.out.println(Thread.currentThread().getName() + ": " + count);
                    count++;
                    lock.notify();
                    if (count < 100){
                        try {
                            //如果任务没结束，让出当前锁
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
