package thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhuchunliu on 2017/2/24.
 */
public class MyThreadPool {

    public static void main(String[] args) throws Exception{

        // ScheduledExecutorService比Timer更安全，功能更强大

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("延迟一秒执行");
            }
        },1, TimeUnit.SECONDS);

        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.err.println("延迟一秒，每秒执行一次");
            }
        },1,3,TimeUnit.SECONDS);


        System.err.println(executorService.isShutdown());
        System.err.println(executorService.isTerminated());
    }
}
