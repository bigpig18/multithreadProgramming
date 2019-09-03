/**
 * 可重入粒度测试：调用父类的方法
 * @author li
 * @date 2019/9/3
 */
public class SynchronizedSuperClass12 {

    public synchronized void doSomething(){
        System.out.println("i am father");
    }
}

class TestClass extends SynchronizedSuperClass12{

    public static void main(String[] args) {
        TestClass testClass = new TestClass();
        testClass.doSomething();
    }

    /**
     * 证明可重入不要求是同一个类
     */
    @Override
    public synchronized void doSomething(){
        System.out.println("i am son");
        super.doSomething();
    }
}
