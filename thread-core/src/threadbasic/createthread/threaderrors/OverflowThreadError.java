package threadbasic.createthread.threaderrors;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述: 发布溢出
 *
 * @author li
 * @date 2019/10/12
 */
public class OverflowThreadError {

    /**
     *  private 本意是不让外部访问到
     */
    private Map<String,String> states;

    public OverflowThreadError() {
        states = new HashMap<>();
        states.put("1","周一");
        states.put("2","周二");
    }

    /**
     * 这个方法把states 暴露出去了
     * @return states
     */
    public Map<String,String> getStates(){
        return states;
    }

    /**
     * 用副本的方法解决该线程安全问题
     * @return map
     */
    public Map<String,String> getStatesImproved(){
        //这样就是只传一个副本出去  外部不能修改 private 修饰的 states
        return new HashMap<>(states);
    }

    public static void main(String[] args) {
        OverflowThreadError error = new OverflowThreadError();
        Map<String,String> map = error.getStates();
        System.out.println(map.get("1"));
        //在这里  我们可以修改private 修饰的变量
        map.remove("1");
        System.out.println(error.getStates().get("1"));

        map = error.getStatesImproved();
        System.out.println(map.get("2"));
        //用了副本的方法 看是否还会被修改private修饰的变量
        map.remove("2");
        System.out.println(error.getStatesImproved().get("2"));
    }
}
