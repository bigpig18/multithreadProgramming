package threadbasic.createthread.threaderrors;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 描述: 运行结果出错
 * 演示计数不准确(减少)，找出具体出错的位置
 *
 * @author li
 * @date 2019/10/11
 */
public class MultiThreadsError implements Runnable{

    private static final MultiThreadsError INSTANCE = new MultiThreadsError();
    private int index = 0;
    private final boolean[] marked = new boolean[100000];

    private static AtomicInteger realIndex = new AtomicInteger();
    private static AtomicInteger wrongCount = new AtomicInteger();

    private static volatile CyclicBarrier cyclicBarrier1 = new CyclicBarrier(2);
    private static volatile CyclicBarrier cyclicBarrier2 = new CyclicBarrier(2);

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(INSTANCE);
        Thread t2 = new Thread(INSTANCE);

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("表面结果: " + INSTANCE.index);
        System.out.println("真实运行次数: " + realIndex.get());
        System.out.println("错误次数: " + wrongCount.get());
    }

    @Override
    public void run() {
        marked[0] = true;
        for (int i = 0; i < 10000; i++) {
            try {
                cyclicBarrier2.reset();
                cyclicBarrier1.await();

                index++;

                cyclicBarrier1.reset();
                cyclicBarrier2.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
            realIndex.incrementAndGet();
            synchronized (INSTANCE) {
                if (marked[index] && marked[index - 1]){
                    System.out.println("发生了错误: " + index);
                    wrongCount.incrementAndGet();
                }
                marked[index] = true;
            }
        }
    }
}
