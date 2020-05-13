package threadbasic.createthread.uncaughtexception;

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
        //子线程抛出异常，主线程未受影响
        //子线程抛出的异常日志很有可能被覆盖在海量日志中
        //所以说  主线程可以轻松发现异常，但是子线程不行
        //同时  子线程异常我们无法用传统的方法捕获
        throw new RuntimeException();
    }
}
