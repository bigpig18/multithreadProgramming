package createthread.stopthread.volatiledemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 用中断来修复之前无尽等待的问题
 * @author li
 * @date 2019/9/19
 */
public class WrongWayVolatileFixed {
    public static void main(String[] args) throws InterruptedException {
        WrongWayVolatileFixed body = new WrongWayVolatileFixed();
        ArrayBlockingQueue storage = new ArrayBlockingQueue(10);
        //生产者
        Producer producer = body.new Producer(storage);
        Thread producerThread = new Thread(producer);
        producerThread.start();
        Thread.sleep(1000);
        //消费者
        Consumer consumer = body.new Consumer(storage);
        while(consumer.needMoreNum()){
            System.out.println(consumer.storage.take()+"--被消费");
            Thread.sleep(100);
        }
        System.out.println("消费者不需要更多数据了");
        // 用interrupt方法
        producerThread.interrupt();
    }



class Producer implements Runnable{

    /**
     * 阻塞队列
     */
    private BlockingQueue storage;

    public Producer(BlockingQueue storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        int num = 0;
        try {
            while(num <= 1000000 && !Thread.currentThread().isInterrupted()){
                if (num % 100 == 0){
                    //当消费者不需要更多的数据后，生产者响应中断,停止生产
                    storage.put(num);
                    System.out.println(num+"---100倍数--被放入仓库");
                }
                num ++;
            }
            Thread.sleep(100);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            System.out.println("生产者stop...");
        }
    }
}

class Consumer{
    /**
     * 阻塞队列
     */
    BlockingQueue storage;

    public Consumer(BlockingQueue storage) {
        this.storage = storage;
    }

    public boolean needMoreNum(){
        if (Math.random()>0.95){
            return false;
        }
        return true;
    }
}
}