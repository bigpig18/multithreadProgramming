/**
 * 可重入粒度测试：调用类内另外的方法
 * @author li
 * @date 2019/9/3
 */
public class SynchronizedOtherMethod11 {

    public static void main(String[] args) {
        SynchronizedOtherMethod11 method11 = new SynchronizedOtherMethod11();
        method11.method1();
    }

    /**
     * 证明可重入不要求是同一个方法
     */
    public synchronized void method1(){
        System.out.println("i am method1");
        method2();
    }

    private synchronized void method2() {
        System.out.println("i am method2");
    }
}
