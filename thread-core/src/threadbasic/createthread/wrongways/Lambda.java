package threadbasic.createthread.wrongways;

/**
 * Lambda 表达式创建线程
 * @author li
 * @date 2019/9/10
 */
public class Lambda {

    public static void main(String[] args) {
        new Thread(()-> System.out.println(Thread.currentThread().getName())).start();
    }
}
