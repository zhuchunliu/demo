package thread;

/**
 * Created by zhuchunliu on 2017/6/2.
 */
public class MyThreadDemo {

    public class ThreadDemo implements Runnable{
        public ThreadDemo(){
            System.err.println(Thread.currentThread().getName()+"构造函数");
        }
        @Override
        public void run() {
            System.err.println(Thread.currentThread().getName()+"run");
        }
    }

    public static void main(String[] args) {
        new Thread(new MyThreadDemo().new ThreadDemo()).start();
        new Thread(new MyThreadDemo().new ThreadDemo(),"abc").start();
    }
}
