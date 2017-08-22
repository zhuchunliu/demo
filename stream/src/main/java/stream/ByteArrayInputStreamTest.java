package stream;

import java.io.ByteArrayInputStream;

/**
 * Created by zhuchunliu on 2017/8/11.
 */
public class ByteArrayInputStreamTest {
    public static void main(String[] args) {
        String str="abcdefghijk";
        ByteArrayInputStream stream = new ByteArrayInputStream(str.getBytes());
        byte[] bytes1 = new byte[2];

        byte[] bytes2 = new byte[str.length()];

        stream.read(bytes1,0,bytes1.length);
        stream.reset();
        stream.read(bytes2,0,bytes2.length);

        System.err.println(new String(bytes1)+"  "+new String(bytes2));
    }
}
