package okhttp;

/**
 * Created by zhuchunliu on 2017/8/10.
 */
public class ObjTest {
    class test {
        private String str;

        String getstr() {
            return str;
        }
    }

    public static void main(String[] args) {
        ObjTest.test obj= new ObjTest().new test();
        String str = obj.getstr();
        str = "abc";
        System.err.println(obj.str);
    }
}
