package thread.new_test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zhuchunliu on 2017/9/4.
 */
public class Service {
    AtomicInteger intex = new AtomicInteger(0);

    public  void un_synchronized(){
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.err.println(Thread.currentThread().getName() + "  " + intex.addAndGet(2));
        }
    }

     public  void test(){
         synchronized(this) {
             for (int i = 0; i < 100; i++) {
                 try {
                     Thread.sleep(1000);
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
                 System.err.println(Thread.currentThread().getName() + "  " + i);
             }
         }
    }

    public  void test_copy(){
        synchronized(this) {
            for (int i = 0; i < 40; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.err.println(Thread.currentThread().getName() + " copy  " + i);
            }
        }
    }

    public synchronized static void test_static(){
        for (int i = 0; i < 99; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.err.println(Thread.currentThread().getName() + " static " + i);
        }
    }
}
