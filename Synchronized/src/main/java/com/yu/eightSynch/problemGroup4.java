package com.yu.eightSynch;

import java.util.concurrent.TimeUnit;

/*
 * 八锁问题：
 * 7、同一个对象，在send个方法上加static关键字 ;  1、打电话 2、发短信
 * 8、两个对象，在send个方法上加static关键字;     1、打电话 2、发短信
 * */
public class problemGroup4 {
    public static void main(String[] args) {
//        两个对象，但是锁的是同一个class类模板
        Phone4 phone1 = new Phone4();
        Phone4 phone2 = new Phone4();
        new Thread(() -> {
            phone1.send();
        }, "A").start();
        //如果不加锁，则谁先抢到资源谁先执行
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            phone2.call();
        }, "B").start();
    }
}

class Phone4 {
    //这里synchronized关键字锁的是方法的调用者，即上面的phone1
    //两个方法用的是同一把锁，谁先拿到谁先执行，但是这里有static关键字，静态方法随着类的加载而加载
    //这里锁的是全局唯一Phone3.class类模板
    public static synchronized void send() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
//        System.out.println("发短信"+Thread.currentThread().getName());
    }

    public synchronized void call() {
        System.out.println("打电话");
    }
}
