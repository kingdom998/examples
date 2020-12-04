package com.kingdom.test;

import java.util.Date;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

class TThread {
}

class TwoThreadAlive extends Thread {
    public void run() {
        for (int i = 0; i < 4; i++) {
            printMsg();
        }
    }

    public void printMsg() {
        Thread t = Thread.currentThread();
        String name = t.getName();
        System.out.println("name=" + name);
    }

    public static void main(String[] args) {
        TwoThreadAlive tt = new TwoThreadAlive();
        tt.setName("Thread");
        System.out.println("before start(), tt.isAlive()=" + tt.isAlive());
        System.out.println("just after start(), tt.isAlive()=" + tt.isAlive());
        for (int i = 0; i < 5; i++) {
            tt.printMsg();
        }

        tt.start();
        System.out.println("The end of main(), tt.isAlive()=" + tt.isAlive());
    }
}



/**
 * 设置优先级
 */
class SimplePriorities extends Thread {
    private int countDown = 5;
    private volatile double d = 0;

    public SimplePriorities(int priority) {
        setPriority(priority);
        start();
    }

    public String toString() {
        return super.toString() + ": " + countDown;
    }

    public void run() {
        while (true) {
            for (int i = 1; i < 100000; i++)
                d = d + (Math.PI + Math.E) / (double) i;
            System.out.println(this);
            if (--countDown == 0) return;
        }
    }

    public static void main(String[] args) {
        new SimplePriorities(Thread.MAX_PRIORITY);
        for (int i = 0; i < 2; i++)
            new SimplePriorities(Thread.MIN_PRIORITY);
    }
}

/**
 * 模拟死锁的产生
 */
class LockTest {
    public static String obj1 = "obj1";
    public static String obj2 = "obj2";
    public static void main(String[] args) {
        new LockTest().test();
    }

    public void test() {
        LockA la = new LockA();
        new Thread(la).start();
        LockB lb = new LockB();
        new Thread(lb).start();
    }

    class LockA implements Runnable{
        public void run() {
            try {
                System.out.println(new Date().toString() + " LockA 开始执行");
                while(true){
                    synchronized (LockTest.obj1) {
                        System.out.println(new Date().toString() + " LockA 锁住 obj1");
                        Thread.sleep(300); // 此处等待是给B能锁住机会
                        synchronized (LockTest.obj2) {
                            System.out.println(new Date().toString() + " LockA 锁住 obj2");
                            Thread.sleep(60 * 1000); // 为测试，占用了就不放
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class LockB implements Runnable{
        public void run() {
            try {
                System.out.println(new Date().toString() + " LockB 开始执行");
                while(true){
                    synchronized (LockTest.obj2) {
                        System.out.println(new Date().toString() + " LockB 锁住 obj2");
                        Thread.sleep(300); // 此处等待是给A能锁住机会
                        synchronized (LockTest.obj1) {
                            System.out.println(new Date().toString() + " LockB 锁住 obj1");
                            Thread.sleep(60 * 1000); // 为测试，占用了就不放
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}


/**
 * 解锁
 */
class UnLockTest {
    public static String obj1 = "obj1";
    public static final Semaphore a1 = new Semaphore(1);
    public static String obj2 = "obj2";
    public static final Semaphore a2 = new Semaphore(1);

    public static void main(String[] args) {
        new UnLockTest().test();
    }

    public void test() {
        LockAa la = new LockAa();
        new Thread(la).start();
        LockBb lb = new LockBb();
        new Thread(lb).start();
    }

    class LockAa implements Runnable {
        public void run() {
            try {
                System.out.println(new Date().toString() + " LockA 开始执行");
                while (true) {
                    if (UnLockTest.a1.tryAcquire(1, TimeUnit.SECONDS)) {
                        System.out.println(new Date().toString() + " LockA 锁住 obj1");
                        if (UnLockTest.a2.tryAcquire(1, TimeUnit.SECONDS)) {
                            System.out.println(new Date().toString() + " LockA 锁住 obj2");
                            Thread.sleep(6 * 1000); // do something
                        }else{
                            System.out.println(new Date().toString() + "LockA 锁 obj2 失败");
                        }
                    }else{
                        System.out.println(new Date().toString() + "LockA 锁 obj1 失败");
                    }
                    UnLockTest.a1.release(); // 释放
                    UnLockTest.a2.release();
                    Thread.sleep(1000); // 马上进行尝试，现实情况下do something是不确定的
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class LockBb implements Runnable {
        public void run() {
            try {
                System.out.println(new Date().toString() + " LockB 开始执行");
                while (true) {
                    if (UnLockTest.a2.tryAcquire(1, TimeUnit.SECONDS)) {
                        System.out.println(new Date().toString() + " LockB 锁住 obj2");
                        if (UnLockTest.a1.tryAcquire(1, TimeUnit.SECONDS)) {
                            System.out.println(new Date().toString() + " LockB 锁住 obj1");
                            Thread.sleep(6 * 1000); // do something
                        }else{
                            System.out.println(new Date().toString() + "LockB 锁 obj1 失败");
                        }
                    }else{
                        System.out.println(new Date().toString() + "LockB 锁 obj2 失败");
                    }
                    UnLockTest.a1.release(); // 释放
                    UnLockTest.a2.release();
                    Thread.sleep(10 * 1000); // 这里只是为了演示，所以tryAcquire只用1秒，而且B要给A让出能执行的时间，否则两个永远是死锁
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}


/**
 * 获取线程id
 */
class GetThreadId extends Object implements Runnable {
    private ThreadID var;

    public GetThreadId(ThreadID v) {
        this.var = v;
    }

    public void run() {
        try {
            print("var getThreadID =" + var.getThreadID());
            Thread.sleep(500);
            print("var getThreadID =" + var.getThreadID());
        } catch (InterruptedException x) {
        }
    }

    private static void print(String msg) {
        String name = Thread.currentThread().getName();
        System.out.println(name + ": " + msg);
    }

    public static void main(String[] args) {
        ThreadID tid = new ThreadID();
        GetThreadId shared = new GetThreadId(tid);

        try {
            Thread threadA = new Thread(shared, "threadA");
            threadA.start();
            Thread.sleep(500);

            Thread threadB = new Thread(shared, "threadB");
            threadB.start();
            Thread.sleep(500);

        } catch (InterruptedException x) {
        }
    }

    static class ThreadID extends ThreadLocal {
        private int nextID;

        public ThreadID() {
            nextID = 10001;
        }

        private synchronized Integer getNewID() {
            Integer id = new Integer(nextID);
            nextID++;
            return id;
        }


        protected Object initialValue() {
            print("in initialValue()");
            return getNewID();
        }

        public int getThreadID() {
            Integer id = (Integer) get();
            return id.intValue();
        }

        private static void print(String msg) {
            String name = Thread.currentThread().getName();
            System.out.println(name + ": " + msg);
        }
    }
}

/**
 * 线程挂起
 */
class SleepingThread extends Thread {
    private int countDown = 5;
    private static int threadCount = 0;

    public SleepingThread() {
        super("" + ++threadCount);
        start();
    }

    public String toString() {
        return "#" + getName() + ": " + countDown;
    }

    public void run() {
        while (true) {
            System.out.println(this);
            if (--countDown == 0)
                return;
            try {
                sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args)
            throws InterruptedException {
        for (int i = 0; i < 3; i++)
            new SleepingThread().join();
        System.out.println("线程已被挂起");
    }
}

/**
 * 终止线程
 */
class ThreadInterrupt extends Thread {
    public void run() {
        try {
            sleep(50000);  // 延迟50秒
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {
        Thread thread = new ThreadInterrupt();
        thread.start();
        System.out.println("在50秒之内按任意键中断线程!");
        System.in.read();
        thread.interrupt();
        thread.join();
        System.out.println("线程已经退出!");
    }
}

class ProducerConsumerTest {
    public static void main(String[] args) {
        new ProducerConsumerTest().test();
    }

    public void test() {
        CubbyHole c = new CubbyHole();
        Producer p1 = new Producer(c, 1);
        Consumer c1 = new Consumer(c, 1);
        p1.start();
        c1.start();
    }


    class CubbyHole {
        private int contents;
        private boolean available = false;

        public synchronized int get() {
            while (available == false) {
                try {
                    wait();
                }
                catch (InterruptedException e) {
                }
            }
            System.out.println("get: " + available);
            available = false;
            notifyAll();
            return contents;
        }

        public synchronized void put(int value) {
            while (available == true) {
                try {
                    wait();
                }
                catch (InterruptedException e) {
                }
            }
            System.out.println("put: " + available);
            contents = value;
            available = true;
            notifyAll();
        }
    }

    class Consumer extends Thread {
        private CubbyHole cubbyhole;
        private int number;
        public Consumer(CubbyHole c, int number) {
            cubbyhole = c;
            this.number = number;
        }
        public void run() {
            int value;
            for (int i = 0; i < 5; i++) {
                value = cubbyhole.get();
                System.out.println("消费者 #" + this.number+ " got: " + value);
            }
        }
    }

    class Producer extends Thread {
        private CubbyHole cubbyhole;
        private int number;

        public Producer(CubbyHole c, int number) {
            cubbyhole = c;
            this.number = number;
        }

        public void run() {
            for (int i = 0; i < 4; i++) {
                cubbyhole.put(i);
                System.out.println("生产者 #" + this.number + " put: " + i);
                try {
                    sleep((int)(Math.random() * 100));
                } catch (InterruptedException e) { }
            }
        }
    }
}

/**
 * 获取线程状态
 */
class GetStatus {
    public static void main(String args[])
            throws Exception{
        new GetStatus().test();
    }

    public void test() throws InterruptedException {
        MyThread th = new MyThread();
        th.setName("MyThread #1");
        showThreadStatus(th);

        th.start();
        Thread.sleep(30);
        showThreadStatus(th);

        th.waiting = false;
        Thread.sleep(50);
        showThreadStatus(th);

        th.notice();
        Thread.sleep(50);
        showThreadStatus(th);

        while(th.isAlive()) {
            System.out.println("alive");
            Thread.sleep(500);
        }
        showThreadStatus(th);
    }

    static void showThreadStatus(Thread thrd) {
        System.out.println(thrd.getName()+" 存活:" +thrd.isAlive()+" 状态:" + thrd.getState() );
    }

    class MyThread extends Thread{
        boolean waiting= true;
        boolean ready= false;
        MyThread() {}

        public void run() {
            String thrdName = Thread.currentThread().getName();
            System.out.println(thrdName + " 启动");
            while(waiting)
            {
                try {
                    System.out.println("等待："+waiting);
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("等待...");
            startWait();
            try {
                Thread.sleep(500);
            }
            catch(Exception exc) {
                System.out.println(thrdName + " 中断。");
            }
            System.out.println(thrdName + " 结束。");
        }

        synchronized void startWait() {
            try {
                while(!ready) wait();
            }
            catch(InterruptedException exc) {
                System.out.println("wait() 中断。");
            }
        }

        synchronized void notice() {
            ready = true;
            notify();
        }
    }

}

/**
 * 获取所有线程
 */
class GetAllThreads extends Thread {
    public static void main(String[] args) {
        GetAllThreads t1 = new GetAllThreads();
        t1.setName("thread1");
        t1.start();
        ThreadGroup currentGroup =
                Thread.currentThread().getThreadGroup();
        int num = currentGroup.activeCount();
        Thread[] threads = new Thread[num];
        currentGroup.enumerate(threads);
        for (int i = 0; i < num; i++)
            System.out.println("线程号：" + i + " = " + threads[i].getName());
    }
}


/**
 * 中断线程
 */
class StopThread extends Object implements Runnable {
    public void run() {
        Thread t = Thread.currentThread();
        // 获取优先级
        System.out.println("in run() - priority="
                + t.getPriority() + ", name=" + t.getName());

        try {
            System.out.println("in run() - 将运行 work() 方法");
            work();
            System.out.println("in run() - 从 work() 方法回来");
        } catch (InterruptedException x) {
            System.out.println("in run() - 中断 work() 方法");
            return;
        }
        System.out.println("in run() - 休眠后执行");
        System.out.println("in run() - 正常离开");
    }

    public void work() throws InterruptedException {
        while (true) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("in work");
                System.out.println("C isInterrupted()=" + Thread.currentThread().isInterrupted());
                Thread.sleep(2000);
                System.out.println("D isInterrupted()=" + Thread.currentThread().isInterrupted());
            }
        }
    }

    public static void main(String[] args) {
        StopThread si = new StopThread();
        Thread t = new Thread(si);
        t.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException x) {
        }
        System.out.println("in main() - 中断其他线程");
        t.interrupt();
        System.out.println("in main() - 离开");
    }
}