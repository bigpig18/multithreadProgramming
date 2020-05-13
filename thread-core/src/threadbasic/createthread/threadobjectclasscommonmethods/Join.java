package threadbasic.createthread.threadobjectclasscommonmethods;

/**
 * 演示join用法，注意语句输出顺序，会变化
 * @author li
 * @date 2019/9/25
 */
public class Join {

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"开始运行...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " -- 休眠完毕");
        },"Bb");

        Thread thread2 = new Thread(()->{
            System.out.println(Thread.currentThread().getName()+"开始运行...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " -- 休眠完毕");
        },"Aa");

        thread1.start();
        thread2.start();
        System.out.println("开始等待子线程运行完毕");
        thread1.join();
        thread2.join();
        System.out.println("所有线程执行完毕");
    }
}
