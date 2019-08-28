/**
 * 方法抛出异常后，会释放锁
 * @author li
 * @date 2019/8/28
 */
public class SynchronizedException9 implements Runnable {

    private static SynchronizedException9 instance = new SynchronizedException9();

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
        if (Thread.currentThread().getName().equals("Thread-0")){
            method1();
        }else {
            method2();
        }
    }

    private synchronized void method1(){
        System.out.println("lock1:" + Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("runtime");
//        System.out.println(Thread.currentThread().getName() + "--lock1---run finish");
    }

    private synchronized void method2(){
        System.out.println("lock2:" + Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "--lock2---run finish");
    }
}
