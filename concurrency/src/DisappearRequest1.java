/**
 * 消失的请求(线程不安全)
 * @author li
 * @date 2019/8/28
 */
public class DisappearRequest1 implements Runnable{

    static DisappearRequest1 instance = new DisappearRequest1();

    private static int i = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(instance);
        Thread t2 = new Thread(instance);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        //预期i是两百万
        System.out.println(i);
    }

    @Override
    public void run() {
        for (int j = 0;j < 1000000;j++){
            i++;
        }
    }
}
