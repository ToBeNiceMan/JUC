package com.yu.eightSynch;

import java.util.concurrent.TimeUnit;

/*
 * 八锁问题：
 * 3、同一个对象，在Phone中增减了一个普通方法hello后; 1、hello 2、发短信
 * 4、两个对象，执行不同对象的不同的同步方法;1、打电话 2、发短信
 * */
public class problemGroup2 {
    public static void main(String[] args) {
//        两个对象
        Phone2 phone1 = new Phone2();
        Phone2 phone2 = new Phone2();
        new Thread(()->{phone1.send();},"A").start();
        //如果不加锁，则谁先抢到资源谁先执行
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        new Thread(()->{phone1.hello();},"B").start();
        new Thread(()->{phone2.call();},"B").start();
    }
}

class Phone2{
    //这里synchronized关键字锁的是方法的调用者，即上面的phone1
    //两个方法用的是同一把锁，谁先拿到谁先执行
    public synchronized void send(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
//        System.out.println("发短信"+Thread.currentThread().getName());
    }

    public synchronized void call(){
        System.out.println("打电话");
    }

    //这里没有锁，不是同步方法，不受锁的影响
    public void hello(){
        System.out.println("hello");
    }
}
