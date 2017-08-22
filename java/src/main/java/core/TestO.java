package core;

/**
 * Created by zhuchunliu on 2017/6/6.
 */
public class TestO {
    class TestO_1 extends Thread{
        boolean flag = true;
        @Override
        public void run() {
            System.err.println("begin");
            while(flag){

            }
            System.err.println("end");
            super.run();
        }

    }

    public static void main(String[] args) {
        TestO.TestO_1 testO_1 = new TestO().new TestO_1();
        testO_1.start();
        testO_1.flag=false;
    }
}
