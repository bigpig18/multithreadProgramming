package juc.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * 描述: 原子数组
 *
 * @author liyun
 * @date 2020/5/17 12:54
 */
public class AtomicArrayDemo {

    public static void main(String[] args) {
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(10);

        Incrementer incrementer = new Incrementer(atomicIntegerArray);
        Decrementer decrementer = new Decrementer(atomicIntegerArray);

        Thread[] threadsIncrementer = new Thread[100];
        Thread[] threadsDecmenter = new Thread[100];

        for (int i = 0; i < 100; i++) {
            threadsIncrementer[i] = new Thread(incrementer);
            threadsDecmenter[i] = new Thread(decrementer);
            threadsIncrementer[i].start();
            threadsDecmenter[i].start();
        }

        for (int i = 0; i < 100; i++) {
            try {
                threadsIncrementer[i].join();
                threadsDecmenter[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < atomicIntegerArray.length(); i++) {
            if (atomicIntegerArray.get(i) != 0){
                System.out.println("发生了错误: " + i);
            }
        }

        System.out.println("运行完毕.");
    }
}

class Decrementer implements Runnable{

    private AtomicIntegerArray array;

    public Decrementer(AtomicIntegerArray array) {
        this.array = array;
    }

    @Override
    public void run() {
        for (int i = 0; i < array.length(); i++) {
            array.getAndDecrement(i);
        }
    }
}

class Incrementer implements Runnable{

    private AtomicIntegerArray array;

    public Incrementer(AtomicIntegerArray array) {
        this.array = array;
    }

    @Override
    public void run() {
        for (int i = 0; i < array.length(); i++) {
            array.getAndIncrement(i);
        }
    }
}
