package threadbasic.createthread.threadobjectclasscommonmethods;

/**
 * 证明wait只释放当前那把锁
 * @author li
 * @date 2019/9/20
 */
public class WaitNotifyReleaseOwnMonitor {

    private static volatile Object obj1 = new Object();
    private static volatile Object obj2 = new Object();

    public static void main(String[] args){
        Thread t1 = new Thread(() -> {
            synchronized (obj1){
                System.out.println("t1 got obj1 ...");
                synchronized (obj2){
                    System.out.println("t1 got obj2 ...");
                    try {
                        System.out.println("t1 releases obj1 ...");
                        obj1.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (obj1){
                System.out.println("t2 got obj1 ...");
            }
            System.out.println("t2 try to get obj2 ...");
            synchronized (obj2){
                System.out.println("t2 got obj2 ...");
            }
        });

        t1.start();
        t2.start();
    }
}
