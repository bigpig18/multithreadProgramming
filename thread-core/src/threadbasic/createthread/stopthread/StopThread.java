package threadbasic.createthread.stopthread;

/**
 * 错误的停止方法: 用stop()来停止线程，会导致线程运行一般突然停止，
 * 无法完成一个基本单位的操作，会造成脏数据
 * @author li
 * @date 2019/9/18
 */
public class StopThread implements Runnable {

    public static void main(String[] args) {
        Thread thread = new Thread(new StopThread());
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.stop();
    }

    @Override
    public void run() {
        // 模拟指挥军队：五个10人的连队，以连队为单位，发当武器弹药，叫到号的士兵去领取
        for (int i = 0; i < 5; i++) {
            System.out.println("连队- " + i + " -开始领取");
            for (int j = 0; j < 10; j++) {
                System.out.println("士兵-" + j + "- 领弹药");
                try {
                    Thread.sleep(90);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("连队- " + i + " -领取完毕");
        }
    }
}
