package singleton;

/**
 * 描述: 懒汉式(线程不安全) 不可用
 *
 * @author li
 * @date 2019/10/18
 */
public class SingletonLazy1 {

    private static SingletonLazy1 instance;

    private SingletonLazy1(){}

    /**
     * 这个方法，我们考虑两个线程竞争的情况
     * @return instance
     */
    public static SingletonLazy1 getInstance(){
        //两个线程同时到达这个判断语句，都判断instance为空了，然后都执行下班的语句
        if (instance == null){
            //而后两个线程都创建了instance实例 这个时候就不符合单例的需求了
            instance = new SingletonLazy1();
        }
        return instance;
    }
}
