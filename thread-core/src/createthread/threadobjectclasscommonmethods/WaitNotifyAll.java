package createthread.threadobjectclasscommonmethods;

/**
 * 3个线程，线程1 2 被阻塞，线程3来唤醒他们(notify,notifyAll)
 * start先执行，不代表线程先启动
 * @author li
 * @date 2019/9/20
 */
public class WaitNotifyAll implements Runnable{

    private static final Object res1 = new Object();

    public static void main(String[] args) throws InterruptedException {
        Runnable r = new WaitNotifyAll();
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        Thread t3 = new Thread(() -> {
            synchronized (res1){
                res1.notifyAll();
                //res1.notify();
                System.out.println("Thread3 notifyAll...");
            }
        });

        t1.start();
        t2.start();
        Thread.sleep(2000);
        t3.start();
    }

    @Override
    public void run() {
        synchronized (res1){
            System.out.println(Thread.currentThread().getName() + " got res1");
            try {
                System.out.println(Thread.currentThread().getName() + " wait to start");
                res1.wait();
                System.out.println(Thread.currentThread().getName() + " waiting to end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
