package core;

/**
 * Created by zhuchunliu on 2017/6/1.
 */
public class TestH {

    private static int age =10;
      void test(String value){
        switch (value){
            case "ab":
                System.err.println("ab");
                break;
            default:
                System.err.println("default");
                break;
        }
    }

    private TestH testH = null;
    private TestH(){

    }

    public static void main(String[] args) {
        TestH testH = new TestH();
        testH.age++;
        testH.test("abc");
        System.err.println(TestH.age);
        assert 1+1 ==3:"中国";
        System.err.println("111");
    }
}
