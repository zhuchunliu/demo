package thread;

/**
 * Created by zhuchunliu on 2017/6/3.
 */
public class Run {
    public class CountOperate extends Thread{

        public CountOperate(){
            System.out.println("CountOperate---begin");
            System.out.println("Thread.currentThread().getName()=" + Thread.currentThread().getName());//获取线程名
            System.out.println("Thread.currentThread().isAlive()=" + Thread.currentThread().isAlive()); //查看线程是否存活
            System.out.println("this.getName=" + this.getName());
            System.out.println("this.isAlive()=" + this.isAlive());
            System.out.println("CountOperate---end ");
            System.out.println("Thread.currentThread()==this :"+ (Thread.currentThread() == this));
        }

        @Override
        public void run() {
            System.out.println("run---begin");
            System.out.println("Thread.currentThread().getName=" + Thread.currentThread().getName());
            System.out.println("Thread.currentThread().isAlive()" + Thread.currentThread().isAlive());
            System.out.println("Thread.currentThread()==this :"+ (Thread.currentThread() == this));
            System.out.println("this.getName()=" + this.getName());
            System.out.println("this.isAlive()=" + this.isAlive());
            System.out.println("run --- end");
        }
    }

    public static void main(String[] args){

        Run.CountOperate c = new Run().new CountOperate();
        c.start();
        c.setName("jack++++++");
        Thread t1 = new Thread(c);
        System.out.println("main begin t1 isAlive=" + t1.isAlive());
        t1.setName("A");
        t1.start();
        System.out.println("main end t1 isAlive=" + t1.isAlive());

    }
}
