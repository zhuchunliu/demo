package thread;

import com.sun.org.apache.xpath.internal.operations.Bool;

import static java.lang.Thread.currentThread;

/**
 * Created by zhuchunliu on 2017/6/3.
 */
public class ThreadInterrupted {
    public class test extends Thread{
        public volatile Boolean flag = true;
        @Override
        public void run() {
            System.err.println((this == Thread.currentThread())+"是否相同");
            super.run();

            for(int i = 0 ; i < 1000; i++){
                if(!flag ){//|| this.isInterrupted()){
                    System.err.println("停止线程");
                    break;
                }

                System.err.println(" i: "+i);
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        ThreadInterrupted.test thread = new ThreadInterrupted().new test();
        System.err.println(thread.getName()+"  "+thread.getId()+"====");
        thread.setName("jack");
//        thread.setDaemon(true);
        thread.start();
//        Thread.sleep(1);
//        thread.interrupt();
//        thread.stop();
//        thread.flag=false;
        System.out.print("线程结束，不要守护");

        System.out.println(thread.isInterrupted()+" --- ");
        System.out.println(thread.isInterrupted()+" == ");
    }
}
