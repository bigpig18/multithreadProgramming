package threadbasic.createthread.startthread;

/**
 * 对比start和run这两种启动线程的方式
 * @author li
 * @date 2019/9/10
 */
public class StartAndRunThread {

    public static void main(String[] args) {
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName());
        };
        runnable.run();

        new Thread(runnable).start();
    }
}
