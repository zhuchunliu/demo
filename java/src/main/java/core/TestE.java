package core;

/**
 * Created by zhuchunliu on 2017/6/1.
 */
public class TestE {
    private static int car = 10;
    class TestE_1{
        public int age = 10;
        public void fa(){
            System.err.println("父类方法");
        }
    }
    class TestE_2 extends TestE_1{
        public  int age = 100;
        public void fa(){
            System.err.println("子类方法");
        }
    }

    static class TestE_3 {
        static int age = 120;
    }
    public static void main(String[] args) {
        TestE_1 testE_1 = new TestE().new TestE_2();
        //10 成员变量的值取决于定义的变量类型，而不是创建对象的类型
        System.err.println(testE_1.age);
        testE_1.fa(); // 子类方法，方法覆盖重载
    }
}
