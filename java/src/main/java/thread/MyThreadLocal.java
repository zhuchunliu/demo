package thread;

/**
 * Created by zhuchunliu on 2017/1/19.
 */
public class MyThreadLocal {

    ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            System.err.println(Thread.currentThread().getName()+"调用");
            return 0;
        }
    };

    private int count =0;



    public class MyThread extends Thread{

        private MyThreadLocal myThreadLocal;
        public MyThread(MyThreadLocal myThreadLocal){
            this.myThreadLocal = myThreadLocal;
        }

        @Override
        public void run() {
            for(int i=0; i<3; i++){
                threadLocal.set(threadLocal.get()+1);
                myThreadLocal.count++;//测试非threadlocal字段，就变成了全局变量
                System.err.println(Thread.currentThread().getName()+" "+myThreadLocal.threadLocal.get()
                +"  "+myThreadLocal.count);
            }
        }
    }

    public static void main(String[] args) {

        // 一个对象在三个线程中，互相独立
        MyThreadLocal myThreadLocal = new MyThreadLocal();
        Thread thread1 = myThreadLocal.new MyThread(myThreadLocal);
        Thread thread2 = myThreadLocal.new MyThread(myThreadLocal);
        Thread thread3 = myThreadLocal.new MyThread(myThreadLocal);
        thread1.start();
        thread2.start();
        thread3.start();
    }
}

