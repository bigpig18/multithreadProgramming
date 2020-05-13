package threadbasic.createthread;

/**
 * @author li
 * @date 2019/9/7
 */
public class ThreadStyle extends Thread {

    public static void main(String[] args) {
        new ThreadStyle().start();
    }

    @Override
    public void run() {
        System.out.println("the thread create by threadClass");
    }
}
