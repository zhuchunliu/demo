package okhttp;

import okio.Okio;

import java.io.*;
import java.nio.charset.Charset;

/**
 * Created by zhuchunliu on 2017/8/10.
 */
public class OkioTest {
    public static void main(String[] args) throws Exception{
        String str = "{\"openid\":\"8a81c0194e2ed1\"," +
                "\"content\":\"1,2,4,1,2,2,3,1,2,2,1,2,3,1,2,3,1,2,3,1,2,3,1,2,2,3,2,3,1,2,3,1,2\"} ";
        File file = new File("/Users/zhuchunliu/test.txt");
        if(file.exists()) file.delete();
        if(!file.exists()) file.createNewFile();

        OutputStream outputStream = new FileOutputStream(file);
        outputStream.write(str.getBytes());
        outputStream.close();

        InputStream inputStream = new FileInputStream(file);

        String value = Okio.buffer(Okio.source(inputStream)).readString(Charset.defaultCharset());
        Okio.buffer(Okio.source(inputStream)).readString(Charset.defaultCharset());
        System.out.println(value);

        inputStream.close();

        Okio.buffer(Okio.sink(file)).write("abcd".getBytes()).close();

    }
}
