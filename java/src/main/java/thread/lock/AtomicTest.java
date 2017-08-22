package thread.lock;

import sun.misc.Unsafe;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * Created by zhuchunliu on 2017/6/19.
 */
public class AtomicTest {


    public volatile int age = 10;
    public void test(){
        Unsafe unsafe = Unsafe.getUnsafe();
        try {
            unsafe.compareAndSwapInt(this,unsafe.objectFieldOffset
                    (AtomicTest.class.getDeclaredField("age")),10,20);
            System.err.println(unsafe.getInt(this,unsafe.objectFieldOffset
                    (AtomicTest.class.getDeclaredField("age"))));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(10);
        atomicInteger.compareAndSet(10,20);//内存10等于预期10，返回20
        System.err.println(atomicInteger.get());
        atomicInteger.compareAndSet(21,30);//内存20不等于预期21，返回20
        System.err.println(atomicInteger.get());
        new AtomicTest().test();


    }
}
