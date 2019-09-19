package createthread.stopthread.volatiledemo;

/**
 * 演示volatile局限性：看似可行的停止线程的方式
 * @author li
 * @date 2019/9/19
 */
public class WrongWayVolatile implements Runnable{

    private volatile boolean canceled = false;

    public static void main(String[] args) throws InterruptedException {
        WrongWayVolatile aVolatile = new WrongWayVolatile();
        Thread thread = new Thread(aVolatile);
        thread.start();
        Thread.sleep(1000);
        aVolatile.canceled = true;
    }

    @Override
    public void run() {
        int num = 0;
        try {
            while(num <= 100000000 && !canceled){
                if (num % 100 == 0){
                    System.out.println(num+"---100倍数");
                }
                num ++;
            }
            Thread.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
