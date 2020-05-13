package createthread.threaderrors;

/**
 * 描述: 演示死锁
 *
 * @author li
 * @date 2019/10/12
 */
public class DeadLockThreadError implements Runnable {

    private int flag = 1;
    private static final Object OBJ1 = new Object();
    private static final Object OBJ2 = new Object();

    public static void main(String[] args) {
        DeadLockThreadError r1 = new DeadLockThreadError();
        DeadLockThreadError r2 = new DeadLockThreadError();
        r1.flag = 0;
        r2.flag = 1;
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
    }

    @Override
    public void run() {
        System.out.println("flag: " + flag);
        if (flag == 1){
            synchronized (OBJ1){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (OBJ2){
                    System.out.println(1);
                }
            }
        }
        if (flag == 0){
            synchronized (OBJ2){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (OBJ1){
                    System.out.println(0);
                }
            }
        }
    }
}
