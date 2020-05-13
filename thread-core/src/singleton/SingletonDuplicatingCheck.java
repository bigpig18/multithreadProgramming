package singleton;

/**
 * 描述: 双重检查锁模式
 * 优点：
 * 1.线程安全
 * 2.延迟加载
 * 3.效率较高
 * @author li
 * @date 2019/10/18
 */
public class SingletonDuplicatingCheck {

    /**
     * 这里加上volatile关键字是为了防止NPE，即空指针错误
     * 这是因为 创建对象的时候有三个步骤:
     * 1.为对象分配空间 2.调用构造函数 3.让对象引用指向该对象(这个时候instance就不为null了)
     * 可是 1 2 3 步骤在 jvm或者cpu的重排序下有可能变成 1 3 2
     * 这个时候 线程1 进入instance = new SingletonDuplicatingCheck() 语句，并执行了 1 3 步骤 ，还未给对象用构造函数创建对象里面的东西
     * 然后突然切换到 线程2 线程2 在第一重判断 instance == null 这里，因为线程1 执行了3 步骤，instance不为空了 直接就返回给线程2 一个
     * 还未经过 2 步骤的实例，这是用去操作实例，就会产生 NPE 错误了
     */
    private volatile static SingletonDuplicatingCheck instance;

    private SingletonDuplicatingCheck(){}

    public static SingletonDuplicatingCheck getInstance(){
        //假设有两个线程1，2 当两个线程都过了第一层判断
        if (instance == null){
            //假设线程1先拿到了锁，线程2后拿到
            synchronized(SingletonDuplicatingCheck.class){
                //当线程1进来了这里，instance是null的，所以去创建了一个实例
                //当线程2进来了这里，这个时候instance是不为null的，于是直接返回了实例
                if (instance == null) {
                    instance = new SingletonDuplicatingCheck();
                }
            }
        }
        return instance;
    }
}
