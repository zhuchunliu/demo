package collection;

import lombok.Data;

import java.util.*;

/**
 * Created by zhuchunliu on 2017/1/10.
 */
public class CollectionTest {

    public static void main(String[] args) {
        Set set = new HashSet();
        set.add(2);set.add(1);set.add(3);
        for(Object object : set){
            System.err.println(" hashset "+object);
        }

        Set tree = new TreeSet();
        tree.add(2);tree.add(1);tree.add(3);
        for(Object object : tree){
            System.err.println(" treeset "+object);
        }

        Set link = new LinkedHashSet();
        link.add(2);link.add(1);link.add(3);
        for(Object object : link){
            System.err.println(" linkedHashSet "+object);
        }


        Set<CollectionTest.Person> treeSet = new TreeSet();
        treeSet.add(new CollectionTest.Person("jack",23));
        treeSet.add(new CollectionTest.Person("tom",33));
        treeSet.add(new CollectionTest.Person("helen",18));
        for(CollectionTest.Person object : treeSet){
            System.err.println(" treeset "+object.getName()+"   "+object.getAge());
        }

        Set<CollectionTest.Person> hashSet = new HashSet<Person>();
        hashSet.add(new CollectionTest.Person("jack",23));
        hashSet.add(new CollectionTest.Person("tom",33));
        hashSet.add(new CollectionTest.Person("helen",18));
        for(CollectionTest.Person object : hashSet){
            System.err.println(" hashSet "+object.getName()+"   "+object.getAge());
        }
    }

    @Data
    public static class Person implements Comparable{
        String name;
        Integer age;

        public Person(String name ,Integer age){
            this.name = name; this.age = age;
        }

        public int compareTo(Object o) {
            CollectionTest.Person p = (CollectionTest.Person)o;
            System.out.println(this.age +" === "+p.getAge());
            return this.age - p.getAge(); // this < o，则返回负数，升序
        }

        public String toString(){
            return this.name+" 的年龄："+this.age;
        }
    }
}
