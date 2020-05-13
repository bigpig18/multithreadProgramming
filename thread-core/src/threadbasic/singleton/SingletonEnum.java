package threadbasic.singleton;

/**
 * 描述: 枚举单例 生产实践用
 *
 * @author li
 * @date 2019/10/18
 */
public enum SingletonEnum {
    //这里就创建出来单例了，要使用的话 就直接  SingletonEnum.INSTANCE.whatEver();就可以了
    INSTANCE;

    private void whatEver(){
        System.out.println("无实际意思，为丰富单例模式的内容写的方法");
    }
}
