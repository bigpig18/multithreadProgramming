package threadbasic.createthread.uncaughtexception;

/**
 * 使用自己写的handler
 * @author li
 * @date 2019/9/26
 */
public class UseOwnUncaughtExceptionHandler implements Runnable{
    public static void main(String[] args) throws InterruptedException {
        Thread.setDefaultUncaughtExceptionHandler(new MyUncaughtExceptionHandler("捕获器"));
        new Thread(new UseOwnUncaughtExceptionHandler(),"Thread-1").start();
        Thread.sleep(3000);
        new Thread(new UseOwnUncaughtExceptionHandler(),"Thread-2").start();
        Thread.sleep(3000);
        new Thread(new UseOwnUncaughtExceptionHandler(),"Thread-3").start();
        Thread.sleep(3000);
        new Thread(new UseOwnUncaughtExceptionHandler(),"Thread-4").start();
    }

    @Override
    public void run() {
        //解决方法 调用UncaughtExceptionHandler（自己实现）
        throw new RuntimeException();
    }
}
