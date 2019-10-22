package deadlock;

import java.util.Random;
import deadlock.TransferAccounts.Account;

/**
 * 描述: 模拟多人同时转账，依然很危险
 *
 * @author li
 * @date 2019/10/22
 */
public class MultiTransferAccounts {

    private static final int NUM_ACCOUNTS = 5000;
    private static final int NUM_MONEY = 1000;
    private static final int NUM_ITERATIONS = 100000;
    private static final int NUM_THREADS = 20;

    public static void main(String[] args) {
        Random rnd = new Random();
        //首先初始化我们的账户
        Account[] accounts = new Account[NUM_ACCOUNTS];
        int len = accounts.length;
        for (int i = 0; i < len; i++) {
            accounts[i] = new Account(NUM_MONEY);
        }

        class TransferThread extends Thread{
            @Override
            public void run() {
                //多个账户进行转账操作
                for (int i = 0; i < NUM_ITERATIONS; i++) {
                    int fromAcc = rnd.nextInt(NUM_ACCOUNTS);
                    int toAcc = rnd.nextInt(NUM_ACCOUNTS);
                    int amount = rnd.nextInt(NUM_MONEY);
                    TransferAccounts.transferMoney(accounts[fromAcc],accounts[toAcc],amount);
                }
                System.out.println("运行完毕...");
            }
        }
        //模拟多个线程转账
        for (int i = 0; i < NUM_THREADS; i++) {
            new TransferThread().start();
        }
    }
}
