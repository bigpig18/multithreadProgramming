package deadlock;

/**
 * 描述: 演示哲学家就餐的问题导致的死锁
 *
 * @author li
 * @date 2019/10/23
 */
public class DiningPhilosophers {


    public static void main(String[] args) {
        Philosophers[] philosophers = new Philosophers[5];
        int len = philosophers.length;
        Object[] chopsticks = new Object[len];
        for (int i = 0; i < len; i++) {
            chopsticks[i] = new Object();
        }
        for (int i = 0; i < len; i++) {
            Object leftChopstick = chopsticks[i];
            //防止下标越界
            Object rightChopstick = chopsticks[(i+1) % len];

            //改变哲学家拿筷子的顺序(避免死锁)
            if (i == len -1){
                philosophers[i] = new Philosophers(rightChopstick,leftChopstick);
            }else {
                philosophers[i] = new Philosophers(leftChopstick,rightChopstick);
            }

            new Thread(philosophers[i],"哲学家"+ (i + 1)).start();
        }
    }

    public static class Philosophers implements Runnable{

        private final Object rightChopstick;
        private final Object leftChopstick;

        public Philosophers(Object rightChopstick, Object leftChopstick) {
            this.rightChopstick = rightChopstick;
            this.leftChopstick = leftChopstick;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    doAction("Thinking");
                    //吃饭的时候，首先拿起左边的筷子
                    synchronized (leftChopstick){
                        doAction("Picked up left Chopstick");
                        //拿到了左边的筷子呢，就把右边的筷子拿起来
                        synchronized (rightChopstick){
                            doAction("Picked up right Chopstick");

                            doAction("Put down right Chopstick");
                        }
                        doAction("Put down left Chopstick");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void doAction(String action) throws InterruptedException {
            System.out.println(Thread.currentThread().getName() + ": " + action);
            Thread.sleep((long) (Math.random()*10));
        }
    }
}
