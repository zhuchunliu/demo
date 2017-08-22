package core;

/**
 * Created by zhuchunliu on 2017/6/13.
 */
public class Test02 extends Test01{
    public Test02(){
        super(100);
        System.err.println("02");
    }

    static {
        System.err.println("子类静态块");
    }

    public  void print01(){
        System.err.println("02方法一");
        Test03.print01();
    }

    public static void main(String[] args) {
        Test03.print01();
        Test03.print02();
        new Test02().print01();
    }
}
