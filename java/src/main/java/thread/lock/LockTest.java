package thread.lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by zhuchunliu on 2017/6/19.
 */
public class LockTest {
    public static void main(String[] args) {
//        LockService service = new LockService();
//        LockThread lockThread1 = new LockThread(service);
//        LockThread lockThread2 = new LockThread(service);
//        LockThread lockThread3 = new LockThread(service);
//        lockThread1.start();
//        lockThread2.start();
//        lockThread3.start();

        ExecutorService executors = Executors.newFixedThreadPool(10);
        executors.submit(new Runnable() {
            @Override
            public void run() {

            }
        });

    }
}
