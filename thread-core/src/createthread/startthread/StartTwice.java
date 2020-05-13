package createthread.startthread;

/**
 * 演示不能两次调用start方法
 * @author li
 * @date 2019/9/10
 */
public class StartTwice {

    public static void main(String[] args) {
        Thread thread = new Thread();
        thread.start();
        thread.start();
    }
}
