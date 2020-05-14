package juc.threadlocal;

/**
 * 描述: 演示使用ThreadLocal空指针问题
 *
 * @author liyun
 * @date 2020/5/14 14:32
 */
public class ThreadLocalNPE {

    ThreadLocal<Long> local = new ThreadLocal<>();

    private void set(){
        local.set(Thread.currentThread().getId());
    }

    /**
     * 如果返回的是long类型的 而不是Long 会因为装箱拆箱导致空指针异常
     * @return Long
     */
    private Long get(){
        return local.get();
    }

    public static void main(String[] args) {
        ThreadLocalNPE npe = new ThreadLocalNPE();

        System.out.println(npe.get());

        Thread thread1 = new Thread(() -> {
            npe.set();
            System.out.println(npe.get());
        });
        thread1.start();
    }
}
