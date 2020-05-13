package threadbasic.createthread.uncaughtexception;

/**
 * 不加try/catch 抛出4个异常
 * 加了try/catch 期望捕获到第一个线程的异常，线程234不应该运行，并希望看到打印出的Caught Exception
 * 执行时发现，根本无Caught Exception ，234依旧运行并抛出异常
 * 说明子线程异常，不能用传统方法捕获
 * @author li
 * @date 2019/9/26
 */
public class CantCatchDirectly implements Runnable{

    public static void main(String[] args) throws InterruptedException {
        //try/catch 只能捕获对应线程内的异常
        try {
            new Thread(new CantCatchDirectly(),"Thread-A").start();
            Thread.sleep(3000);
            new Thread(new CantCatchDirectly(),"Thread-B").start();
            Thread.sleep(3000);
            new Thread(new CantCatchDirectly(),"Thread-C").start();
            Thread.sleep(3000);
            new Thread(new CantCatchDirectly(),"Thread-D").start();
        } catch (RuntimeException e) {
            System.out.println("Caught Exception...");
        }
    }

    @Override
    public void run() {
        //解决方法 1.在run方法里面放try/catch (不推荐)
//        try {
            throw new RuntimeException();
//        } catch (RuntimeException e) {
//            System.out.println("Caught Exception...");
//        }
        //方法 2.调用UncaughtExceptionHandler（自己实现）
    }
}
