package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by zhuchunliu on 2017/6/2.
 */
public class TestM extends TestN{
    private void A_test(){

    }
    private static void B_test(){

    }
    public static void main(String[] args) {
        List list = new ArrayList();
        list = Collections.synchronizedList(list);
    }
}
