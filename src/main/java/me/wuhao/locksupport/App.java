package me.wuhao.locksupport;

import java.util.concurrent.locks.LockSupport;

/**
 * Created by everseeker on 16/8/10.
 */
public class App {

    public static void main(String[] args) throws InterruptedException {
        MyThread a = new MyThread("Thread-A");
        a.start();

        MyThread b = new MyThread("Thread-B");
        b.start();

        Thread.sleep(3000);

        //线程通过调用park而阻塞的话, 可以通过unpark取消阻塞, 也可以通过中断请求来取消阻塞
        LockSupport.unpark(a);
        b.interrupt();
    }
}

class MyThread extends Thread {
    public MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();

        System.out.println(threadName + "    1");
        LockSupport.park();

        System.out.println(threadName + "    2");
    }
}
