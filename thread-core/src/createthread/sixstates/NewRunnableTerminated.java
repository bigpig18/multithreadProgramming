package createthread.sixstates;

/**
 * 展示线程的NEW RUNNABLE TERMINATED 三种状态
 * 即使是正在运行，线程也是处于 RUNNABLE 状态
 * @author li
 * @date 2019/9/19
 */
public class NewRunnableTerminated implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new NewRunnableTerminated());
        //线程未start时的状态
        System.out.println(thread.getState());
        thread.start();
        //线程start之后的状态
        System.out.println(thread.getState());
        Thread.sleep(5);
        //线程运行中的状态
        System.out.println(thread.getState());
        Thread.sleep(1000);
        System.out.println(thread.getState());
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(i);
        }
    }
}
