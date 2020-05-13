package createthread;

/**
 * 用runnable方式创建线程
 * @author li
 * @date 2019/9/7
 */
public class RunnableStyle implements Runnable {

    public static void main(String[] args) {
        Thread t1 = new Thread(new RunnableStyle());
        t1.start();
    }

    @Override
    public void run() {
        System.out.println("the thread create by runnable");
    }
}
