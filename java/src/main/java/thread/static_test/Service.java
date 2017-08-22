package thread.static_test;

/**
 * Created by zhuchunliu on 2017/6/5.
 */
public class Service {
    synchronized public  static void methodA(){
        System.err.println("A方法begin");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.err.println("A方法end");
    }

    synchronized public  static void methodB(){
        System.err.println("B方法begin");
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.err.println("B方法end");
    }

    synchronized public  static void methodC(){
            System.err.println("C方法begin");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.err.println("C方法end");
        }
    }

