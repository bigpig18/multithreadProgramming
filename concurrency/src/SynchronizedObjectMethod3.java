/**
 * 对象锁，方法锁形式
 * @author li
 * @date 2019/8/28
 */
public class SynchronizedObjectMethod3 implements Runnable {

    static SynchronizedObjectMethod3 instance = new SynchronizedObjectMethod3();

    public static void main(String[] args) {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        while (t1.isAlive() || t2.isAlive()){}
        System.out.println("all finish...");
    }

    @Override
    public void run() {
        try {
            method();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private synchronized void method() throws InterruptedException {
        System.out.println("objectLock---method:"+Thread.currentThread().getName());
        Thread.sleep(2000);
        System.out.println(Thread.currentThread().getName()+" is finish...");
    }
}
