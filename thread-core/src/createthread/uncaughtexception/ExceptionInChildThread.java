package createthread.uncaughtexception;

/**
 * 多线程情况下，子线程发生异常，会有什么不同
 * @author li
 * @date 2019/9/26
 */
public class ExceptionInChildThread implements Runnable{

    public static void main(String[] args) {
        Thread t1 = new Thread(new ExceptionInChildThread());
        t1.start();
        for (int i = 0; i < 10000; i++) {
            System.out.println("main: " + i);
        }
    }

    @Override
    public void run() {
        throw new RuntimeException();
    }
}
