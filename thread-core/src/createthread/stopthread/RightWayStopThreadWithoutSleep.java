package createthread.stopthread;

/**
 * run方法内没有sleep或wait方法时停止线程
 * @author li
 * @date 2019/9/10
 */
public class RightWayStopThreadWithoutSleep implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadWithoutSleep());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }

    @Override
    public void run() {
        int num = 0;
        while (!Thread.currentThread().isInterrupted() && num <= (Integer.MAX_VALUE /2)){
            if (num % 10000 == 0){
                System.out.println(num + "是10000的倍数");
            }
            num++;
        }
        System.out.println("the task is finish");
    }
}
