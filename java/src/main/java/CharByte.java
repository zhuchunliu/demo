import com.google.common.collect.Sets;

import java.util.Set;

/**
 * Created by zhuchunliu on 2016/12/28.
 */
public class CharByte {
    public static void main(String[] args) {
        char c1 = '中';
        byte b1 = -127;
        char c2 = 10;

        byte[] bytes = new byte[20];

        System.err.println(bytes.length);

        String str = "abcdefg";
        System.err.println(str.substring(2,5));

        Set set = Sets.newHashSet(1,4,3);
        for(Object obj : set ){
            System.err.println("obj "+obj);
        }

    }
}
