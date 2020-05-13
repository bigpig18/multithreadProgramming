package threadbasic.createthread.threaderrors.solution;


/**
 * 描述: 还未初始化(构造函数没完全执行完毕)就把对象提供给了外界
 * (工厂模式 解决方法)
 * @author li
 * @date 2019/10/14
 */
public class RegisterListenerErrorSolution {
    int count;

    private EventListener eventListener;

    //先讲构造函数变为private 保护起来
    private RegisterListenerErrorSolution(Source source) {
        eventListener = new EventListener() {
            @Override
            public void OnEvent(Event e) {
                System.out.println("\n我得到的数字是: "+count);
            }
        };
        for (int i = 0; i < 10000; i++) {
            System.out.print(i);
        }
        count = 100;
    }

    public static RegisterListenerErrorSolution getInstance(Source source){
        //在之前的类中 count还未被初始化就被过早的发布出去了
        //现在使用工厂模式  solution首先被创建出来
        RegisterListenerErrorSolution solution = new RegisterListenerErrorSolution(source);
        //完成了准备工作之后  才注册  现在count是被初始化了被发布的
        source.registerListener(solution.eventListener);
        return solution;
    }

    public static void main(String[] args) {
        Source mySource = new Source();
        RegisterListenerErrorSolution error = getInstance(mySource);
        new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            mySource.eventCome(new Event(){});
        }).start();

    }

    static class Source{
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
