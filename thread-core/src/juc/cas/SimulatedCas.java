package juc.cas;

/**
 * 描述: 模拟cas操作
 *
 * @author liyun
 * @date 2020/6/10 22:03
 */
public class SimulatedCas {

    private volatile int value;

    public synchronized int compareAndSwap(int expectedValue,int newValue){
        int oldValue = value;
        if (oldValue == expectedValue){
            value = newValue;
        }
        return oldValue;
    }
}
