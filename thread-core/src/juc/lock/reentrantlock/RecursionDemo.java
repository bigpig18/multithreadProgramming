package juc.lock.reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述: 递归
 *
 * @author liyun
 * @date 2020/5/15 12:31
 */
public class RecursionDemo {

    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        accessResource();
    }

    private static void accessResource(){
        lock.lock();
        try{
            System.out.println("已经对资源进行处理");
            if (lock.getHoldCount() < 5){
                System.out.println(lock.getHoldCount());
                accessResource();
            }
        }finally {
            lock.unlock();
        }
    }
}
