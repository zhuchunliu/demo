package core;

/**
 * Created by zhuchunliu on 2017/6/1.
 */
public class TestD {
    public static void main(String[] args) throws Exception{
        TestC testC = new TestC();
        TestC testC1 = testC;
        TestC testC2 = (TestC) testC.clone();
        testC.changeAge();
        System.err.println(testC.getAge());  // 返回100
        System.err.println(testC1.getAge()); // 返回100
        System.err.println(testC2.getAge()); // 返回0
    }
}
