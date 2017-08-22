package core;

/**
 * Created by zhuchunliu on 2017/6/1.
 */
public class TestF {
    class TestF_1{
    }
    class TestF_2 extends TestF.TestF_1 {
        public void test(){
            //java中任何类都继承Object类，getClass类被定义成final和native,子类不能覆盖
            //因此this.getClass和super.getClass都是调用的Object.getClass方法
            //Object.getClass：返回此Object的运行时类，当前运行类是TestF_2
            System.err.println(this.getClass().getName()+" 和 "+super.getClass().getName()); //返回：都是testF_2
            System.err.println(this.getClass().getSuperclass().getName());//返回TestF_1;
        }
    }
    public static void main(String[] args) {
        new TestF().new TestF_2().test();
    }
}
