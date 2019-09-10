package createthread.wrongways;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 定时器创建线程
 * @author li
 * @date 2019/9/10
 */
public class TimmerTask {

    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        },1000,1000);
    }
}
