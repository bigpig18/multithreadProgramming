package threadbasic.createthread.threaderrors;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述: 构造函数中新建线程
 *
 * @author li
 * @date 2019/10/12
 */
public class ConstructorThreadError {
    private Map<String,String> states;

    public ConstructorThreadError() {
        //子线程赋值会有一定的延迟，当赋值未完成，但有其他线程访问，这里会出现空指针异常
        new Thread(() -> {
            states = new HashMap<>();
            states.put("1","周一");
            states.put("2","周二");
        }).start();
    }

    public Map<String,String> getStates(){
        return states;
    }

    public static void main(String[] args) throws InterruptedException {
        ConstructorThreadError error = new ConstructorThreadError();
        //当程序不等待子线程赋值完成，会有空指针错误 Thread.sleep(100);
        Map<String,String> map = error.getStates();
        System.out.println(map.get("1"));
    }
}
