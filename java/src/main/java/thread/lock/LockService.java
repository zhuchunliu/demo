package thread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by zhuchunliu on 2017/6/19.
 */
public class LockService {
    private Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    public void testMethod(){
        lock.lock();

        for(int i= 0 ; i < 5;i++){
            System.out.println(Thread.currentThread().getName()+" i: "+i);
        }
//        lock.unlock();
    }
}
