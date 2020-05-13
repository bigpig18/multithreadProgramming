package threadbasic.singleton;

/**
 * 描述: 懒汉式(线程安全,同步方法) 不推荐用
 *
 * @author li
 * @date 2019/10/18
 */
public class SingletonLazy2 {

    private static SingletonLazy2 instance;

    private SingletonLazy2(){}

    /**
     * 在这里，虽然加了synchronized 关键字修饰，实现了线程安全
     * 但是现在问题是效率太低了
     * @return instance
     */
    public synchronized static SingletonLazy2 getInstance(){
        if (instance == null){
            instance = new SingletonLazy2();
        }
        return instance;
    }
}
