/**
 * 反编译字节码
 * @author li
 * @date 2019/9/3
 */
public class Decompilation14 {

    private final Object object = new Object();

    /**
     * monitorenter monitorexit
     * @param thread thread
     */
    public void insert(Thread thread){
        synchronized (object){

        }
    }
}
