package jmm;

/**
 * 描述: 可见性带来的问题
 * </h2>为什么会有可见性问题：</h2>
 * CPU有多级缓存，导致读的数据过期
 * <h2>具体描述：</h2>
 *  用 JMM 中主内存和本地内存解释
 *  所有的共享变量都存在与主内存中，每个线程呢都有自己的工作内存，
 *  且线程间共享读写共享数据也是要通过主内存交换的，所以导致了可见性问题.
 *
 *  拿本类例子来说...变量 a,b 都在主存中，第一个线程 change()方法中的操作，
 *  需要先读取主存中的a,b 然后操作两个值，再讲其写入到主存中，这个时候第二个
 *  线程使用print()方法打印两个值，b已经被第一个线程写到了主存，而a没有被写入，
 *  这个时候就打印出 b = 3,a = 1 的结果，这就是可见性问题了
 * @author li
 * @date 2019/10/16
 */
public class FieldVisibility {

    private int a = 1;
    private int b = 2;


    private void print() {
        // 由于可见性问题  有可能会打印出 b = 3,a = 1 的情况
        System.out.println("b = " + b + ",a = " + a);
    }

    private void change() {
        a = 3;
        b = a;
    }

    public static void main(String[] args) {
        while (true) {
            FieldVisibility test = new FieldVisibility();
            new Thread(() -> {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                test.change();
            }).start();

            new Thread(() -> {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                test.print();
            }).start();
        }
    }
}
