package reflect;

import lombok.Data;

/**
 * Created by zhuchunliu on 2017/1/12.
 */
@Data
public class Person implements China{
    private String name;
    public Integer age;

    public Person(){

    }

    public Person(String name, int age) {
        this.age=age;
        this.name=name;
    }

    public Person(String name) {
        this.name=name;
    }

    public Person(int age) {
        this.age=age;
    }

    public String toString(){
        return this.name+"==="+this.age;
    }

    public void sayChina()  throws NullPointerException{
        System.err.println("中国你好");
    }

    public void sayHello(String name, Integer age) {
        System.err.println(name+"你好，今年"+age+"岁");
    }

    public String sayHello(String name, int age) {
        System.err.println(name+"你好吗，今年"+age+"岁");
        return name;
    }
}
