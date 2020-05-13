package threadbasic.createthread.stopthread;

/**
 * catch住InterruptedException之后的优先选择：在方法签名中抛出异常(传递该中断)
 * @author li
 * @date 2019/9/12
 */
public class RightWayStopThreadInProduct implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadInProduct());
        thread.start();
        System.out.println(Thread.currentThread().getName());
        Thread.sleep(5000);
        System.out.println("---- sleep ----");
        thread.interrupt();
    }

    @Override
    public void run() {
        while(true){
            System.out.println("lalal");
            //在方法签名中抛出异常，那么在run方法中就会强制try/catch处理异常，这样我们就能去响应处理这个中断
            //避免了被漏掉或者被吞掉的可能
            try {
                throwInMethod();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void throwInMethod() throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        Thread.sleep(2000);
    }
}
