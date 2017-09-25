package entity;

import lombok.Data;
import org.msgpack.annotation.Message;

/**
 * Created by zhuchunliu on 2017/8/24.
 */
@Data
@Message
public class Teacher{
    private String name;
    private int age;

    public Teacher(){

    }
    public Teacher(String name,int age){
        this.name = name;
        this.age =age ;
    }

    public String toString(){
        return " name: "+name+"  age: "+age;
    }
}