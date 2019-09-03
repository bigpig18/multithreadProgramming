import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author li
 * @date 2019/9/3
 */
public class SynchronizedToLock13 {

    Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        SynchronizedToLock13 toLock13 = new SynchronizedToLock13();
        toLock13.method1();
        toLock13.method2();
    }

    public synchronized void method1(){
        System.out.println("i am synchronized");
    }

    public void method2(){
        lock.lock();
        try{
            System.out.println("i am lock");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
