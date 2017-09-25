package core;

/**
 * Created by zhuchunliu on 2017/9/6.
 */
public class TestQ {
    void test() throws Exception{
        throw new Exception("test");
    }

    void mian(){
        try{
            try{
                test();
            }finally {

            }
            throw new Exception("mian");
        }catch (Exception ex){
            System.err.println(ex.getMessage());
        }
    }

    class A{
        int i =0;int j=0;
        void incI(){i++;System.err.println("a i");};
        void incJ(){j++;System.err.println("a j");};
    }

    class B extends A{
        int i =10;
        void incI(){i+=5;System.err.println("b i");};
        void incJ(){j+=5;System.err.println("b j");};
    }

    public static void main(String[] args) {
        TestQ testQ = new TestQ();
        A a = new TestQ().new B();
        a.incI();a.incJ();
        System.err.println(a.i+"  "+a.j);
        System.out.println("a 非的结果是："+(~-2));
        System.out.println("a 和b 与的结果是ddd："+(2^3));
        System.out.println("a 和b 与的结果是："+(2<<3));
    }
}
