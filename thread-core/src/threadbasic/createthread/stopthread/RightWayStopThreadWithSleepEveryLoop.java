package threadbasic.createthread.stopthread;

/**
 * 如果在执行过程中，每次循环都会调用sleep或wait,就不需要每次迭代都判断线程是否中断
 * @author li
 * @date 2019/9/10
 */
public class RightWayStopThreadWithSleepEveryLoop {

    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int num = 0;
            try {
                while(num <= 3000){
                    if (num % 100 == 0){
                        System.out.println(num + " is a multiple of 100");
                    }
                    num++;
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(3000);
        thread.interrupt();
    }
}
