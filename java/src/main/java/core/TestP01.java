package core;

import junit.framework.Test;

/**
 * Created by zhuchunliu on 2017/6/13.
 */
public class TestP01 {

    public void init() throws Exception{
//        TestP testP = (TestP) Class.forName("core.TestP").newInstance();// 执行静态代码块方法
        TestP testP01 = (TestP)Class.forName("core.TestP",true,getClass().
                getClassLoader()).newInstance();
//        TestP testP02 = (TestP) getClass().getClassLoader().
//                loadClass("core.TestP").newInstance(); //不执行静态代码块方法

//        testP01.init();
    }

    public static void main(String[] args) throws Exception{
        new TestP01().init();
        System.err.println(System.getProperty("java.util.logging.manager"));
        System.err.println("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuv".length());
        System.err.println(System.getProperty("line.separator"));
    }
}
