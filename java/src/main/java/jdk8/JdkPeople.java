package jdk8;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by zhuchunliu on 2017/1/13.
 */
public class JdkPeople {
    public static void main(String[] args) {



        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");

        Collections.sort(names, new Comparator<String>() {
            public int compare(String a, String b) {
                return b.compareTo(a);
            }
        });
//
//        Collections.sort(names, (String a, String b) -> {
//            return b.compareTo(a);
//        });
    }
}
