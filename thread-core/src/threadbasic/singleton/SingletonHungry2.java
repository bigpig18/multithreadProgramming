package threadbasic.singleton;

/**
 * 描述: 饿汉式(静态代码块) - 可用
 * 这种写法跟第一种十分类似
 * @author li
 * @date 2019/10/18
 */
public class SingletonHungry2 {
    private final static SingletonHungry2 INSTANCE;
    static {
        INSTANCE = new SingletonHungry2();
    }

    private SingletonHungry2(){}

    public static SingletonHungry2 getInstance(){
        return INSTANCE;
    }
}
