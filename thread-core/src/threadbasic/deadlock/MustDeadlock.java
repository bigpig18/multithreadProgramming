package threadbasic.deadlock;

/**
 * 描述: 必定发生死锁的例子
 * 如何定位死锁:
 * 该方法适用比较明显的死锁错误，不过就算检测不出，还是会有方法栈分析的
 * 首先使用 jps 命令查看当前发生死锁程序的pid
 * 使用 jstack pid(刚查出来的pid) 命令，会打印出很详细的方法栈信息
 * @author li
 * @date 2019/10/22
 */
public class MustDeadlock implements Runnable{

    private int flag = 1;

    private static final Object OBJ1 = new Object();
    private static final Object OBJ2 = new Object();

    public static void main(String[] args) {
        MustDeadlock r1 = new MustDeadlock();
        MustDeadlock r2 = new MustDeadlock();
        r1.flag = 1;
        r2.flag = 0;
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
    }

    @Override
    public void run() {
        System.out.println("flag:" + flag);

        if (flag == 1){
            synchronized (OBJ1){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (OBJ2){
                    System.out.println("Thread1 gets two lock2.");
                }
            }
        }

        if (flag == 0){
            synchronized (OBJ2){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (OBJ1){
                    System.out.println("Thread2 gets two lock2.");
                }
            }
        }
    }
}
