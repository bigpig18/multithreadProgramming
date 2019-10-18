package singleton;

/**
 * 描述: 饿汉式(静态常量) - 可用
 * 这种饿汉式模式的有点:
 * 1.实现较简单
 * 2.在类装载的时候就实例化
 * @author li
 * @date 2019/10/18
 */
public class SingletonHungry {

    private final static SingletonHungry INSTANCE = new SingletonHungry();

    private SingletonHungry(){};

    public SingletonHungry getInstance(){
        return INSTANCE;
    }
}
