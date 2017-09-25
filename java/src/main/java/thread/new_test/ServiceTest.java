package thread.new_test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by zhuchunliu on 2017/9/4.
 */
public class ServiceTest {
    public static void main(String[] args) {
        Service service1 = new Service();
        Service service2 = new Service();



        new Thread(new Runnable() {
            @Override
            public void run() {

                service1.un_synchronized();
            }
        },"A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                service1.un_synchronized();
            }
        },"B").start();
    }
}
