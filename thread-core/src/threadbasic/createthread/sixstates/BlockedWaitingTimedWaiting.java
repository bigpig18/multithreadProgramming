package threadbasic.createthread.sixstates;

/**
 * 展示 BLOCKED WAITING TIMED-WAITING 三种状态
 * @author li
 * @date 2019/9/19
 */
public class BlockedWaitingTimedWaiting implements Runnable{

    public static void main(String[] args) throws InterruptedException {
        BlockedWaitingTimedWaiting runnable = new BlockedWaitingTimedWaiting();
        Thread t0 = new Thread(runnable);
        t0.start();
        Thread t1 = new Thread(runnable);
        t1.start();
        System.out.println("t0 - " + t0.getState());
        System.out.println("t1 - " + t1.getState());
        Thread.sleep(500);
        System.out.println("=====================");
        //TIMED_WAITING状态 因为此时的t0正在sleep
        System.out.println("t0 - " + t0.getState());
        //BLOCKED状态，因为此时的t1正在等待t0释放锁
        System.out.println("t1 - " + t1.getState());
        Thread.sleep(2000);
        System.out.println("=====================");
        System.out.println("t0 - " + t0.getState());
        System.out.println("t1 - " + t1.getState());
        Thread.sleep(2000);
        System.out.println("=====================");
        System.out.println("t0 - " + t0.getState());
        System.out.println("t1 - " + t1.getState());
    }

    @Override
    public void run() {
        syn();
    }

    private synchronized void syn(){
        try {
            System.out.println(Thread.currentThread().getName() + " --- syn()");
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " --- overSleep");
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
