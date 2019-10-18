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

    private static SingletonDuplicatingCheck instance;

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
