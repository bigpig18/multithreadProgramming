package createthread.threaderrors;

/**
 * 描述: 注册监听器(观察者模式)
 * 还未初始化(构造函数没完全执行完毕)就把对象提供给了外界
 * @author li
 * @date 2019/10/12
 */
public class RegisterListenerThreadError {

    int count;

    public RegisterListenerThreadError(MySource source) {
        //注册监听器
        source.registerListener(e -> System.out.println("\n我得到的数字是: "+count));
        for (int i = 0; i < 10000; i++) {
            System.out.print(i);
        }
        count = 100;
    }

    public static void main(String[] args) {
        MySource mySource = new MySource();
        new Thread(() -> {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mySource.eventCome(new Event(){});
        }).start();
        //这里使用构造方法 其中count 应该在构造方法中赋值了 是100 但是打印结果可能不一样
        //在这里 还未初始化(构造函数没完全执行完毕)就把对象提供给了外界
        RegisterListenerThreadError error = new RegisterListenerThreadError(mySource);
    }

    static class MySource{
        private EventListener listener;

        void registerListener(EventListener eventListener){
            this.listener = eventListener;
        }

        void eventCome(Event e){
            if (listener != null){
                listener.OnEvent(e);
            }else {
                System.out.println("未初始化...");
            }
        }
    }

    interface EventListener{
        void OnEvent(Event e);
    }

    interface Event{}
}
