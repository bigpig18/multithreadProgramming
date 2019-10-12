package createthread.threaderrors;

/**
 * 描述: 初始化的时候未完毕就this赋值
 *
 * @author li
 * @date 2019/10/12
 */
public class ThisInitThreadError {

    static Point point;

    public static void main(String[] args) throws InterruptedException {
        new PointMaker().start();
        //两段不同时长的休眠时间，x y 值是不同的
        //        Thread.sleep(10);
        Thread.sleep(1000);
        if (point != null){
            System.out.println(point.toString());
        }
    }
}

class Point{
    private final int x,y;

    Point(int x, int y) throws InterruptedException {
        this.x = x;
        //这样赋值会有很大的隐患
        ThisInitThreadError.point = this;
        Thread.sleep(100);
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

class PointMaker extends Thread{
    @Override
    public void run() {
        try {
            new Point(1,1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
