package createthread.threadobjectclasscommonmethods;

/**
 * 展示wait和notify的基本用法
 * 1.研究代码的执行顺序
 * 2.证明wait释放锁
 * @author li
 * @date 2019/9/20
 */
public class Wait {

    private static final Object object = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();
        thread1.start();
        Thread.sleep(500);
        thread2.start();
    }

    static class Thread1 extends Thread{
        @Override
        public void run() {
            synchronized (object){
                System.out.println(Thread.currentThread().getName() + " is start");
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " get the lock");
            }
        }
    }

    static class Thread2 extends Thread{
        @Override
        public void run() {
            synchronized (object){
                System.out.println(Thread.currentThread().getName() + " is start");
                object.notify();
                System.out.println(Thread.currentThread().getName() + " notify...");
            }
        }
    }
}
