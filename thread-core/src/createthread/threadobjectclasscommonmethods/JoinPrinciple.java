package createthread.threadobjectclasscommonmethods;

/**
 * join原理，分析join的代替写法
 * @author li
 * @date 2019/9/26
 */
public class JoinPrinciple {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(()->{
            try {
                Thread.sleep(1000);
                System.out.println("Thread1 finished...");
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("Thread1 is interrupt");
            }
        });
        thread1.start();
        System.out.println("等待线程运行完毕...");
        //等价于thread1.join();
        synchronized (thread1){
            System.out.println(Thread.currentThread().getName()+" is wait");
            thread1.wait();
        }
        System.out.println("子线程运行完毕...");
    }
}
