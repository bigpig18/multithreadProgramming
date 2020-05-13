package threadbasic.deadlock;

import java.util.Random;

/**
 * 描述: 演示活锁问题
 *
 * @author li
 * @date 2019/10/23
 */
public class LiveLock {

    public static void main(String[] args) {
        Diner husband = new Diner("husband");
        Diner wife = new Diner("wife");
        Spoon spoon = new Spoon(husband);

        new Thread(()->{
            husband.eatWith(spoon,wife);
        }).start();
        new Thread(()->{
            wife.eatWith(spoon,husband);
        }).start();
    }

    static class Spoon{
        private Diner owner;

        public Spoon(Diner owner) {
            this.owner = owner;
        }

        public void setOwner(Diner owner) {
            this.owner = owner;
        }

        public synchronized void use(){
            System.out.printf("%s has eaten.\n",owner.name);
        }
    }

    static class Diner{
        private String name;
        private Boolean isHungry;

        public Diner(String name) {
            this.name = name;
            isHungry = true;
        }

        public void eatWith(Spoon spoon, Diner spouse){
            while (isHungry){
                //我饿了，但是没勺子，等勺子
                if (spoon.owner!=this){
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    continue;
                }

                //当无条件给对方勺子的时候，会导致活锁问题
                //现在引入随机值，并非无条件的把勺子给对方
                Random random = new Random();
                //有勺子，但是对方饿了，先让对方吃，并把勺子给对方
                if (spouse.isHungry && random.nextInt(10) < 9){
                    System.out.println(name + ": " + spouse.name + "你先吃..");
                    spoon.setOwner(spouse);
                    continue;
                }
                spoon.use();
                isHungry = false;
                System.out.println(name + "吃完了");
                spoon.setOwner(spouse);
            }
        }
    }
}
