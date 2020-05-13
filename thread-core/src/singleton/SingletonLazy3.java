package singleton;

/**
 * 描述: 懒汉式(线程不安全,同步代码块) 不可用
 *
 * @author li
 * @date 2019/10/18
 */
public class SingletonLazy3 {

    private static SingletonLazy3 instance;

    private SingletonLazy3(){}

    /**
     * 本意是解决之前性能问题及线程安全问题，可是忽略了其他的问题
     * 但是并未解决线程安全问题
     *
     * @return instance
     */
    public static SingletonLazy3 getInstance(){
        if (instance == null){
            //这里其实两个线程都进入到了这个if语句里面来了
            //两个线程还是都会执行instance 实例化语句 所以这里并未解决线程安全问题
            synchronized(SingletonLazy3.class){
                instance = new SingletonLazy3();
            }
        }
        return instance;
    }
}
