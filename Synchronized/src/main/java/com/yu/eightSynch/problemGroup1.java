package com.yu.eightSynch;

import com.sun.xml.internal.bind.v2.model.runtime.RuntimeElement;
import org.omg.SendingContext.RunTime;

import java.util.concurrent.TimeUnit;

/*
* 八锁问题：
* 1、标准方法：两个方法都有关键字synchronized中都没有sleep; 1、发短信 2、打电话
* 2、在send方法中加入sleep;
* */
public class problemGroup1 {
    public static void main(String[] args) {
        Phone1 phone1 = new Phone1();
        new Thread(()->{phone1.send();},"A").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{phone1.call();},"B").start();
//        这里加入一个主线程的输出会在第一种情况下输出send方法后执行
//        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}

class Phone1{
    //这里synchronized关键字锁的是方法的调用者，即上面的phone1
    //两个方法用的是同一把锁，谁先拿到谁先执行
    public synchronized void send(){
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }

    public synchronized void call(){
        System.out.println("打电话");
    }
}