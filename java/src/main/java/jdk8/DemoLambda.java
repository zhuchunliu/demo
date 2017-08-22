package jdk8;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by zhuchunliu on 2017/1/17.
 */
public class DemoLambda {

    @FunctionalInterface
    interface DemoInterface<T>{
        T getAge(String name);

        default String getname(){
            return null;
        }
    }

    public static void main(String[] args) {
        List<String> list = Arrays.asList("123","0123","234");
        Collections.sort(list, new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        List<String> listT = Arrays.asList("123","0123","234");
        Collections.sort(listT, (String o1,String o2)->{
            return o1.compareTo(o2);
        });

        Collections.sort(listT, (String o1,String o2)-> o1.compareTo(o2));

        Collections.sort(listT,  (o1,o2)-> o1.compareTo(o2));



        System.err.println(list);
        System.err.println(listT);


        DemoInterface demoInterface = (name)->Integer.valueOf(name);
        System.err.println(demoInterface.getAge("100"));

        DemoInterface demoInterface1 = Integer::valueOf;
        DemoInterface demoInterface2 = String::length;
        System.err.println(demoInterface1.getAge("123"));
        System.err.println(demoInterface2.getAge(" 123 "));

        String age ="789";
        DemoInterface demoInterface3 = (agex)->Integer.parseInt(agex+age);
        System.err.println(demoInterface3.getAge("99"));



    }


}
