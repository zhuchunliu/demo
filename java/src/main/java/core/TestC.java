package core;

import java.util.Date;

/**
 * Created by zhuchunliu on 2017/6/1.
 */
public class TestC implements Cloneable{
    private int age = 0;
    private Date birth = new Date();

    public int getAge(){
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public void changeAge(){
        this.age = 100;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    protected  Object clone() throws CloneNotSupportedException {
        return (TestC) super.clone();

    }
}
