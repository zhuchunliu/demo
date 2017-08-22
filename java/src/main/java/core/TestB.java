package core;

/**
 * Created by zhuchunliu on 2017/5/31.
 */
public class TestB {

    public int age = 100;

    public TestB(){
        System.out.println("testB默认构造函数");
    }

    public TestB(int age){
//        super();
        System.err.println("当前年龄"+age);
    }
    public TestB(String name){
//        super();
//        this();
        System.err.println("当前 "+name+" 年龄"+age);
    }
    public static void main(String[] args) {

        System.err.println(" testB");
        new TestB("jac");
    }
}
