package jdk8;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zhuchunliu on 2017/1/19.
 */
public class JdkStream {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("aa1","aa","bb","bb1","dd","cc1","cc1","dd1");
        list.stream().filter((s)->s.contains("1")).forEach(System.out::println);
        List list1 = list.stream().filter((s)->s.contains("1")).collect(Collectors.toList());
        System.err.println(list1);

    }
}
