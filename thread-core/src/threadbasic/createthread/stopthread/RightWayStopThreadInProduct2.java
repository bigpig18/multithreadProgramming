package threadbasic.createthread.stopthread;

/**
 * catch住InterruptedException之后可以调用Thread.currentThread().interrupt()
 * 来恢复设置中断状态，以便于在后续执行中，依然能检查到刚刚发生了中断
 * 不能粗暴的屏蔽权限
 * @author li
 * @date 2019/9/12
 */
public class RightWayStopThreadInProduct2 implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadInProduct2());
        thread.start();
        System.out.println(Thread.currentThread().getName());
        Thread.sleep(1000);
        System.out.println("---- sleep ----");
        thread.interrupt();
    }

    @Override
    public void run() {
        while(true){
            if (Thread.currentThread().isInterrupted()){
                System.out.println("the thread is exit");
                break;
            }
            System.out.println("heihei");
            reInterrupt();
        }
    }

    private void reInterrupt(){
        System.out.println(Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            //恢复中断
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}
