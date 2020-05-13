package threadbasic.createthread.threadobjectclasscommonmethods;

/**
 * 演示join期间被中断的效果
 * @author li
 * @date 2019/9/26
 */
public class JoinInterrupt {

    public static void main(String[] args) {
        Thread mainT = Thread.currentThread();
        Thread thread1 = new Thread(()->{
            try {
                mainT.interrupt();
                Thread.sleep(5000);
                System.out.println("Thread1 finished...");
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("Thread1 is interrupt");
            }
        });
        thread1.start();
        System.out.println("等待线程运行完毕...");
        try {
            thread1.join();
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName()+" is interrupted");
            e.printStackTrace();
            thread1.interrupt();
        }
        System.out.println("子线程运行完毕...");
    }
}
