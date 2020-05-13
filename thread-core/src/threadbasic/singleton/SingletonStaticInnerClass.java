package threadbasic.singleton;

/**
 * 描述: 静态内部类方式 可用
 *
 * @author li
 * @date 2019/10/18
 */
public class SingletonStaticInnerClass {

    private SingletonStaticInnerClass(){}

    private static class SingletonInstance{
        private static final SingletonStaticInnerClass INSTANCE = new SingletonStaticInnerClass();
    }

    public SingletonStaticInnerClass getInstance(){
        return SingletonInstance.INSTANCE;
    }
}
