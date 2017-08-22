package thread;

/**
 * Created by zhuchunliu on 2017/6/3.
 */
public class MyObject {
    class Test {
        synchronized public void testA() throws InterruptedException {
            Thread.sleep(1000);
            System.err.println(Thread.currentThread().getName()+"testA方法");
        }

        synchronized public void testB() throws InterruptedException {
            Thread.sleep(1000);
            System.err.println(Thread.currentThread().getName()+"testB方法");
        }

        synchronized public void testC() throws InterruptedException {
            Thread.sleep(1000);
            System.err.println(Thread.currentThread().getName()+"testC方法");
        }
    }

    class MyThread implements Runnable{
        MyObject.Test test = null;
        boolean flag = false;
        public MyThread(MyObject.Test test,Boolean flag){
            this.test = test;
        }

        @Override
        public void run() {
            try {
                test.testA();
                if(flag) Thread.sleep(1000);
                test.testB();
                if(flag) Thread.sleep(1000);
                test.testC();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        MyObject.Test test = new MyObject().new Test();
        MyObject.MyThread thread1 = new MyObject().new MyThread(test,true);
        MyObject.MyThread thread2 = new MyObject().new MyThread(test,false);
        new Thread(thread1,"thread1").start();
        new Thread(thread2,"thread2").start();

    }
}
