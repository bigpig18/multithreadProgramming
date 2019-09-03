/**
 * 可重入粒度测试：递归调用本方法
 * @author li
 * @date 2019/9/3
 */
public class SynchronizedRecursion10 {

    int i = 0;

    public static void main(String[] args) {
        SynchronizedRecursion10 recursion10 = new SynchronizedRecursion10();
        recursion10.method1();
    }

    /**
     * 证明同一个方法是可重入的
     */
    private synchronized void method1() {
        System.out.println("method1---i = " + i);
        if (i==0){
            i++;
            method1();
        }
    }


}
