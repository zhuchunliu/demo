package core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/**
 * Created by zhuchunliu on 2017/6/1.
 */
public class TestK {
    public static void main(String[] args) throws Exception{
        int i = 4;
        int j = -4;
        System.err.println(Integer.toBinaryString(4)+" "+(i >>= 4)+"  "+(4<<3));
        System.err.println(Integer.toBinaryString(-4)+" "+(j>>=1));
        System.err.println(Integer.toBinaryString(2147483644));

        String s1 = new String("abc");
        String s2 = new String("abc");
        System.err.println(s1.hashCode()+"   "+s2.hashCode());

        int[] arr;
        int arr1[] ;
        float a[] = new float[20];
        int b[] = new int[10];
        new TestK().test();
        new ArrayList<>().size();
        s1.length();
        int c = a.length;
    }



        public void test() {
        /*
        Collection c = new HashSet();
        c.add("hello");
        c.add(new Name("f1","l1"));
        c.add(new Integer(100));
        c.remove("hello");
        c.remove(new Integer(100));
        System.out.println(c.remove(new Name("f1","l1")));
        */
            Name n1 = new Name("01");
            Name n2 = new Name("01");


            Collection c = new HashSet();
            c.add(n1);
            System.out.println("------------");
            c.add(n2);
            System.out.println("------------");
            System.out.println(n1.equals(n2));
            System.out.println("------------");
            System.out.println(n1.hashCode());
            System.out.println(n2.hashCode());
            System.out.println(c);
        }



    class Name {
        private String id;
        public Name(String id) {
            this.id = id;
        }

        public String toString(){
            return this.id;
        }
        public boolean equals(Object obj) {
            if (obj instanceof Name) {
                Name name = (Name) obj;
                System.out.println("equal"+ name.id);
                return (id.equals(name.id));
            }
            return super.equals(obj);
        }

        public int hashCode() {
            Name name = (Name) this;
            System.out.println("Hash" + name.id);
            return id.hashCode();

        }
    }
}
