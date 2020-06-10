package juc.cas;

/**
 * 描述: 两个线程竞争
 *
 * @author liyun
 * @date 2020/6/10 22:03
 */
public class TwoThreadsCompetition implements Runnable{

    private volatile int value;

    public static void main(String[] args) throws InterruptedException {
        TwoThreadsCompetition r = new TwoThreadsCompetition();
        r.value = 0;
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(r.value);
    }

    public synchronized int compareAndSwap(int expectedValue,int newValue){
        int oldValue = value;
        if (oldValue == expectedValue){
            value = newValue;
        }
        return oldValue;
    }

    @Override
    public void run() {
        compareAndSwap(0,1);
    }
}
