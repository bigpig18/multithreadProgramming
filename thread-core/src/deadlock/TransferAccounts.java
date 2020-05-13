package deadlock;

/**
 * 描述: 转账时发生死锁，一旦打开注释，便会发生死锁
 * 死锁发生的必要条件:
 * 1.互斥条件: 意思就是这个资源只能一个线程用，这个线程拿了另外的就不能用了
 * 2.请求与保持条件: 就拿这个例子来说，我拿到了from 去请求 to ，请求to 的时候还保持着from
 * 3.不剥夺条件: 没有外力干预，被剥夺现在手里拿着的资源
 * 4.循环等待条件: 这个转账例子中，两个线程构成了一个回路，我等你的锁，你等我的锁，谁也等不到谁
 * @author li
 * @date 2019/10/22
 */
public class TransferAccounts implements Runnable{

    private int flag = 1;

    private static Account a = new Account(500);
    private static Account b = new Account(500);
    private static final Object LOCK = new Object();

    public static void main(String[] args) throws InterruptedException {
        TransferAccounts r1 = new TransferAccounts();
        TransferAccounts r2 = new TransferAccounts();
        r1.flag = 1;
        r2.flag = 0;
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("余额 - a: " + a.balance + " - b: " + b.balance);
    }

    @Override
    public void run() {
        if (flag == 1){
            transferMoney(a,b,200);
        }

        if (flag == 0){
            transferMoney(b,a,200);
        }
    }

    /**
     * 转账方法
     * @param from 钱从哪来
     * @param to 钱往哪去
     * @param amount 转账金额
     */
    static void transferMoney(Account from, Account to, int amount) {
//        class Helper{
//            public void transfer(){
//                if (from.balance - amount < 0){
//                    System.out.println("余额不足，转账失败");
//                }
//                from.balance -= amount;
//                to.balance += amount;
//                System.out.println("转账成功: " + amount +"元");
//            }
//        }
//        //这里用换序的思路来避免死锁(避免相反的获取锁的顺序)
//        int fromHash = System.identityHashCode(from);
//        int toHash = System.identityHashCode(to);
//        if (fromHash < toHash){
//            synchronized (from){
//                synchronized (to){
//                    new Helper().transfer();
//                }
//            }
//        }else if (fromHash > toHash){
//            synchronized (to){
//                synchronized (from){
//                    new Helper().transfer();
//                }
//            }
//        }else{
//            //如果发生hash碰撞 即两个对象的hash值一样
//            //就去竞争另外一把锁，谁拿到谁先转账
//            synchronized (LOCK){
//                synchronized (to){
//                    synchronized (from){
//                        new Helper().transfer();
//                    }
//                }
//            }
//        }
        synchronized (from){
//            这里就是注释了
//            try {
//                Thread.sleep(500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            synchronized (to){
                if (from.balance - amount < 0){
                    System.out.println("余额不足，转账失败");
                }
                from.balance -= amount;
                to.balance += amount;
                System.out.println("转账成功: " + amount +"元");
            }
        }
    }

    static class Account{
        int balance;

        Account(int balance) {
            this.balance = balance;
        }
    }
}
