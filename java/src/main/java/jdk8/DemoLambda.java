package jdk8;

import lombok.Data;

import java.util.*;

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

    static void run(){
        System.err.println("线程");
    }

    public Runnable getRunnable(){
        return DemoLambda::run;
    }

    private void compare_base(){
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

    protected final Comparator<Student> comparator = (a,b)->a.getName().compareTo(b.getName());
    protected static int comparex(Student a ,Student b){
        return a.getName().compareTo(b.getName());
    }
    protected static int compare(Student a ,Student b){
        return a.getName().compareTo(b.getName());
    }

    public static void main(String[] args) {
        DemoLambda lambda = new DemoLambda();
        List list = new ArrayList();
        list.add(new DemoLambda().new Student("tom",30));
        list.add(new DemoLambda().new Student("jack",20));
        list.add(new DemoLambda().new Student("jack",10));
//        Collections.sort(list,lambda.comparator);
//        Collections.sort(list, DemoLambda::comparex);
//        Collections.sort(list, DemoLambda::compare);
        Collections.sort(list,Comparator.comparing(Student::getName).thenComparing(Student::getAge));
        for(Object student : list){
            System.err.println(student.toString());
        }

        new Thread(lambda.getRunnable()).start();

    }

    @Data
    class Student{
        private String name;
        private Integer age;
        public Student(String name ,Integer age){
            this.name = name ;this.age = age;
        }
        public String toString(){
            return this.name+" : "+this.age;
        }


    }


}
