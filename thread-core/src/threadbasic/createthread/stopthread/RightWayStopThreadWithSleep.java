package threadbasic.createthread.stopthread;

/**
 * 带有sleep的中断线程的方法
 * @author li
 * @date 2019/9/10
 */
public class RightWayStopThreadWithSleep {


    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int num = 0;
            try {
                while(!Thread.currentThread().isInterrupted() && num <= 300){
                    if (num % 100 == 0){
                        System.out.println(num + " is a multiple of 100");
                    }
                    num++;
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(500);
        thread.interrupt();
    }
}
