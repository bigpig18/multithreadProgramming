package createthread;

/**
 * 同时使用Runnable和Thread两种方式实现线程
 * @author li
 * @date 2019/9/7
 */
public class BothRunnableThread {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable ...");
            }
        }){
            @Override
            public void run() {
                System.out.println("Thread ...");
            }
        }.start();
    }
}
