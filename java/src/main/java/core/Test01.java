package core;

/**
 * Created by zhuchunliu on 2017/6/13.
 */
public class Test01 {
    private Test01(){
        System.err.println("01");
    }

    static {
        System.err.println("父类静态块");
    }
    public Test01(int age){
        System.err.println("01"+age);
    }
}
