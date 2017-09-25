package core;

import java.util.List;

/**
 * Created by zhuchunliu on 2017/9/4.
 */
public class ClassDefine<T , s extends T> {

    public static void main(String[] args) {
        ClassDefine<List,? extends List> xx = new ClassDefine<>();
    }
}
