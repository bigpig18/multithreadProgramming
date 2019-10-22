package deadlock;

/**
 * 描述: 转账时发生死锁，一旦打开注释，便会发生死锁
 *
 * @author li
 * @date 2019/10/22
 */
public class TransferAccounts implements Runnable{

    private int flag = 1;

    private static Account a = new Account(500);
    private static Account b = new Account(500);

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
    private void transferMoney(Account from, Account to, int amount) {
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
