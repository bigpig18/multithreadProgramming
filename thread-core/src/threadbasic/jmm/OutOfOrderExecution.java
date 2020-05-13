package threadbasic.jmm;

import java.util.concurrent.CountDownLatch;

/**
 * 描述: 演示重排序的现象
 * 知道达到某个条件才停止，用来测试小概率事件
 * @author li
 * @date 2019/10/16
 */
public class OutOfOrderExecution {

    private static int x = 0,y = 0;
    private static int a = 0,b = 0;

    public static void main(String[] args) throws InterruptedException {

        int i = 0;

        for (;;) {
            i++;
            x = 0;
            y = 0;
            a = 0;
            b = 0;
            //工具类 用来辅助实现场景 两个线程几乎同时开始执行
            CountDownLatch latch = new CountDownLatch(1);

            Thread one = new Thread(() -> {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                a = 1;
                x = b;
            });

            Thread two = new Thread(() -> {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                b = 1;
                y = a;
            });

            one.start();
            two.start();
            latch.countDown();
            one.join();
            two.join();

            //在这里  有三种不同的情况  x = 0,y = 1 (第一个线程先运行)
            // x = 1,y = 0 (第二个线程先运行)
            // x = 1,y = 1 (两个线程几乎同时开始执行) 使用工具类 CountDownLatch 还原该场景 可能需要运行多次
            System.out.println("执行了 " + i + " 次: "+ "x = " + x + " ,y = " + y);
            System.out.println();
            // 会出现 x = 0 ,y = 0 的情况 那说明发生了重排序 方法中的其中一种情况的执行顺序 y = a; a = 1; x = b; b = 1;
            if (x == 0 && y == 0){
                break;
            }
        }
    }
}
