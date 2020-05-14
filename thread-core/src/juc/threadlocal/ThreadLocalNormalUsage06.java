package juc.threadlocal;

/**
 * 描述: 演示ThreadLocal的用法2 避免传递参数传递的麻烦
 *
 * @author liyun
 * @date 2020/5/14 12:55
 */
public class ThreadLocalNormalUsage06 {
    public static void main(String[] args) {
        new Service1().process();
    }
}

class User{
    private String name;
    private int age;

    User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

class UserContextHolder{
    public static ThreadLocal<User> holder = new ThreadLocal<>();
}

class Service1{
    public void process(){
        User user = new User("张三",20);
        UserContextHolder.holder.set(user);
        new Service2().testThreadLocal();
    }
}

class Service2{
    public void testThreadLocal(){
        User user = UserContextHolder.holder.get();
        System.out.println("Service2: " + user.toString());
        new Service3().testThreadLocal();
    }
}
class Service3{
    public void testThreadLocal(){
        User user = UserContextHolder.holder.get();
        System.out.println("Service3: " + user.toString());
        // 避免内存泄漏 调用remove方法
        UserContextHolder.holder.remove();
    }
}