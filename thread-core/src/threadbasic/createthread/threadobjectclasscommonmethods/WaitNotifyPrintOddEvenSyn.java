package threadbasic.createthread.threadobjectclasscommonmethods;

/**
 * 两个线程交替打印0~100奇偶数(synchronized 实现)
 * @author li
 * @date 2019/9/25
 */
public class WaitNotifyPrintOddEvenSyn {
    private static int count;
    private static Object lock = new Object();

    //新建两个线程，一个只处理奇数，一个只处理偶数(位运算)
    //用synchronized 来通信
    public static void main(String[] args) {
        new Thread(()->{
           while (count < 100){
                synchronized (lock){
                    if ((count & 1) == 0){
                        System.out.println(Thread.currentThread().getName() + ": "+count);
                        count++;
                    }
                }
           }
        },"偶数Thread").start();
        new Thread(()->{
            while (count < 100){
                synchronized (lock){
                    if ((count & 1) == 1){
                        System.out.println(Thread.currentThread().getName() + ": "+count);
                        count++;
                    }
                }
            }
        },"奇数Thread").start();
    }
}
