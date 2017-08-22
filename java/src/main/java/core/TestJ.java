package core;

/**
 * Created by zhuchunliu on 2017/6/1.
 */
public class TestJ {
    public static void change(StringBuffer ss1,StringBuffer ss2){
        ss1.append(" world");
        ss2 = ss1;
        char s = 100;
    }
    public static void main(String[] args) {
        Integer a =1;
        Integer b = a;
        a++;
        b++;
        System.err.println(a == b);
        System.err.println(a+" == "+b); // 1 == 2
        StringBuffer s1 = new StringBuffer("hello");
        StringBuffer s2 = new StringBuffer("hello");
        TestJ.change(s1,s2);
        System.err.println(s1.toString()+"  ==  "+s2.toString()); // hello world  ==  hello
    }
}
