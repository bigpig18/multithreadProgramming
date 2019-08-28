/**
 * 对象锁，代码块实例
 * @author li
 * @date 2019/8/28
 */
public class SynchronizedObjectCodeBlock2 implements Runnable{

    static SynchronizedObjectCodeBlock2 instance = new SynchronizedObjectCodeBlock2();

    private Object lock1 = new Object();
    private Object lock2 = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        while (t1.isAlive() || t2.isAlive()){

        }
        System.out.println("finish...");
    }

    @Override
    public void run() {
        synchronized (lock1) {
            System.out.println("lock1---ObjectCodeBlock:" + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "----run finish(lock1)");
        }

        synchronized (lock2) {
            System.out.println("lock2---ObjectCodeBlock:" + Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "----run finish(lock2)");
        }
    }
}
