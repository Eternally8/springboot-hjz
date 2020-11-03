package com.jz.example.multiThread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    /**
     * 详细解释ReentrantLock的使用，主要引用了下面两篇博客
     * https://blog.csdn.net/zengmingen/article/details/53260650  lockInterruptibly的使用
     * https://www.cnblogs.com/takumicx/p/9338983.html   ReentrantLock的使用
     */

    private Lock lock = new ReentrantLock();

    public void bFuction() {
        String tName = Thread.currentThread().getName();
        try {
            System.out.println(tName + "-开始获取锁..........");
            lock.lockInterruptibly();

            System.out.println(tName + "-获取到锁了！！！！");
            System.out.println(tName + "-睡觉了，睡个25秒！");
            Thread.sleep(25000);
            System.out.println(tName + "-睡醒了，干活！");
            for (int i = 0; i < 5; i++) {
                System.out.println(tName + "：" + i);
            }
        } catch (Exception e) {
            System.out.println(tName+"-我好像被中断了！");
            e.printStackTrace();
        }finally{
            lock.unlock();
            System.out.println(tName + "-释放了锁");
        }
    }

    //测试lockInterruptibly的使用
    public void testInterruptibly() throws Exception{
        ReentrantLockTest bc = new ReentrantLockTest();
        Thread t0=new Thread(){
            @Override
            public void run() {
                bc.bFuction();
            }
        };

        Thread t1=new Thread(){
            @Override
            public void run() {
                bc.bFuction();
            }
        };

        String tName=Thread.currentThread().getName();

        System.out.println(tName+"-启动t0！");
        t0.start();
        System.out.println(tName+"-我等个5秒，再启动t1");
        Thread.sleep(5000);
        System.out.println(tName+"-启动t1");
        t1.start();

        System.out.println(tName+"-t1获取不到锁，t0这货睡觉了，没释放，我等个5秒！");
        Thread.sleep(5000);
        System.out.println(tName+"-等了5秒了，不等了，把t1中断了！");
        t1.interrupt();

//        Thread.sleep(Long.MAX_VALUE);
    }

    public static void main(String[] args) {
        ReentrantLockTest bc = new ReentrantLockTest();
        try {
            bc.testInterruptibly();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
