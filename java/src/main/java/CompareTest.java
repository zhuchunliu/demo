import com.google.common.collect.Lists;
import lombok.Data;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by zhuchunliu on 2016/12/22.
 */
public class CompareTest {

    @Data
    public static class Teacher{
        String name ;
        Integer age;

        public Teacher(String name ,Integer age){
            this.name = name; this.age = age;
        }


        public String toString(){
            return this.name+" 的年龄："+this.age;
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
            Person p = (Person)o;
            // this < o，则返回负数，升序  看Comparable源码描述【this和指定对象比较，小于=负数，等于=0，大于=正数】
            return this.age < p.getAge()?-1:this.age > p.getAge()?1:0;
        }

        public String toString(){
            return this.name+" 的年龄："+this.age;
        }
    }

    public static void main(String[] args) {
        List<String> list = Lists.newArrayList("a","d","c");
        Collections.sort(list);
        for(String id : list){
            System.err.println(id);
        }

        List<Person> personList = Lists.newArrayList();

        personList.add(new Person("jack",23));
        personList.add(new Person("tom",33));
        personList.add(new Person("helen",18));

        Collections.sort(personList);
        for(Person person : personList){
            System.err.println(person.toString());
        }


        List<Teacher> teacherList = Lists.newArrayList();



        teacherList.add(new Teacher("t_jack",23));
        teacherList.add(new Teacher("t_tom",33));
        teacherList.add(new Teacher("t_helen",18));

        Collections.sort(teacherList, new Comparator<Teacher>() {
            public int compare(Teacher t1, Teacher t2) {
                return t1.getAge() - t2.getAge();
            }
        });
        for(Teacher teacher : teacherList){
            System.err.println(teacher.toString());
        }

        System.err.println(new Person("jack",23).
                compareTo(new Person("helen",18)));

    }
}
