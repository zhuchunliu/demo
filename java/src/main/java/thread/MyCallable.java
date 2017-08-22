package thread;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by zhuchunliu on 2017/2/23.
 */
public class MyCallable {

    private class CallableDemo implements Callable{

        @Override
        public Object call() throws Exception {
            Thread.sleep(3000);
            return Thread.currentThread().getName()+" jack";
        }
    }

    public static void main(String[] args) throws Exception{
        int num =5;
        ExecutorService pool = Executors.newFixedThreadPool(num); // 如果下面加入的线程数超过num，则需要等待，线程名称会相同


        List<Future> list = Lists.newArrayList();
        for(int i=0; i<num; i++){
            CallableDemo demo = new MyCallable().new CallableDemo();
            list.add(pool.submit(demo));
        }
        pool.shutdown();
        System.out.println("========");
        for(Future future : list){
            System.out.println("+++++++++");
            System.err.println(future.get().toString()); // GET方法会引起线程阻塞
        }
        System.out.println("------------");
    }

}
