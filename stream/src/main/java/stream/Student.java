package stream;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by zhuchunliu on 2017/6/2.
 */
@Data
public class Student implements Serializable{

    private static final long serialVersionUID = 2L;

    private int age;
    private String name;
    private Date birth;
    public Student(int age ,String name){
        this.age =age ;
        this.name = name;
        this.birth = new Date();
    }
}
