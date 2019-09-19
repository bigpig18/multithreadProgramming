package createthread.stopthread.volatiledemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 演示volatile局限性：当陷入阻塞中，volatile是无法停止线程的
 * 此例中，生产者生产速度快，消费者消费速度慢，所以阻塞队列满了后，
 * 生产者会阻塞，等待消费者进一步消费
 * @author li
 * @date 2019/9/19
 */
public class WrongWayVolatileCantStop {

    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue storage = new ArrayBlockingQueue(10);
        //生产者
        Producer producer = new Producer(storage);
        Thread producerThread = new Thread(producer);
        producerThread.start();
        Thread.sleep(1000);
        //消费者
        Consumer consumer = new Consumer(storage);
        while(consumer.needMoreNum()){
            System.out.println(consumer.storage.take()+"--被消费");
            Thread.sleep(100);
        }
        System.out.println("消费者不需要更多数据了");
        // 一旦消费者不需要更多数据了，就应该让生产者停下
        // 实际情况  canceled设置为true后，生产者并未停止消费
        producer.canceled = true;
    }

}

class Producer implements Runnable{

    /**
     * 阻塞队列
     */
    private BlockingQueue storage;

    volatile boolean canceled = false;

    public Producer(BlockingQueue storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        int num = 0;
        try {
            while(num <= 1000000 && !canceled){
                if (num % 100 == 0){
                    //当容量满了的时候，会一直阻塞在这里，而canceled虽然被设置为true了
                    //但是并没有去做上面的那个判断，而是一直在这里阻塞着，所以我们遇到
                    //线程的长时间阻塞，就没办法及时唤醒线程，或者永远都无法唤醒该线程
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
