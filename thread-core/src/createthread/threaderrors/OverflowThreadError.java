package createthread.threaderrors;

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
     *  private 本意是不让外部应用访问到
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

    public static void main(String[] args) {
        OverflowThreadError error = new OverflowThreadError();
        Map<String,String> map = error.getStates();
        System.out.println(map.get("1"));
        //在这里  我们可以修改private 修饰的变量
        map.remove("1");
        System.out.println(map.get("1"));
    }
}
