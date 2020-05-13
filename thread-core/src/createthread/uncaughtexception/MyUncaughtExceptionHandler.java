package createthread.uncaughtexception;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 自定义UncaughtExceptionHandler
 * @author li
 * @date 2019/9/26
 */
public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

    private String name;

    public MyUncaughtExceptionHandler(String name) {
        this.name = name;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Logger logger = Logger.getAnonymousLogger();
        logger.log(Level.WARNING,"线程异常，终止了"+t.getName(),e);
        System.out.println(name + " 捕获了"+ t.getName() +"的异常:"+e);

    }
}
