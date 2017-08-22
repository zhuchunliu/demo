package core;

/**
 * Created by zhuchunliu on 2017/5/31.
 */
public class TestA extends TestB{

    static {
        System.err.println("静态块");
    }

    {
        System.err.println("非静态块");
    }
    public TestA(){

//        super("jack");
//        super();
        System.out.println(this.getClass().getName()+"  = "+super.getClass().getName());
        System.err.println("构造函数");
    }

    static synchronized public  final void main(String[] args) {
        new TestA();

//        System.err.println(" testA");
//        System.err.println(new TestA().age);
//        TestB.main(null);
        Thread.currentThread().isInterrupted();
    }


}
