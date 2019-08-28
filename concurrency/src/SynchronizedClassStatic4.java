/**
 * 类锁的第一种形式，static形式
 * @author li
 * @date 2019/8/28
 */
public class SynchronizedClassStatic4 implements Runnable {

    static SynchronizedClassStatic4 instance1 = new SynchronizedClassStatic4();
    static SynchronizedClassStatic4 instance2 = new SynchronizedClassStatic4();

    public static void main(String[] args) {
        Thread t1 = new Thread(instance1);
        Thread t2 = new Thread(instance2);
        t1.start();
        t2.start();
        while (t1.isAlive()||t2.isAlive()){}
        System.out.println("all finish...");
    }

    @Override
    public void run() {
        method();
    }

    private static synchronized void method(){
        System.out.println("ClassLock---static-method:"+Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+" is finish...");
    }
}
