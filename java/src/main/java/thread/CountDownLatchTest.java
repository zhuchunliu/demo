package thread;

import java.util.concurrent.CountDownLatch;

/**
 * Created by zhuchunliu on 2017/8/18.
 */
public class CountDownLatchTest {
    public static void main(String[] args) throws Exception{
        CountDownLatch latch = new CountDownLatch(2);
        new Thread(new Runnable() {
            @Override
            public void run() {
                latch.countDown();
                latch.countDown();
            }
        }).start();
        latch.await();
        System.err.println("123");
    }
}
