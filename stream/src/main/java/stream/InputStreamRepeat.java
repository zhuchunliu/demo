package stream;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Created by zhuchunliu on 2017/8/11.
 */
public class InputStreamRepeat {
    public static void main(String[] args) throws Exception{
        InputStream inputStream = new FileInputStream("/Users/zhuchunliu/test.txt");

        byte[] arr = new byte[1];
        byte[] arr1 = new byte[1];
        inputStream.read(arr,0,arr.length);
        inputStream.skip(-1);
        inputStream.read(arr1,0,arr1.length);

        System.err.println(new String(arr)+"  "+new String(arr1));

//        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
//        bufferedInputStream.read(arr,0,arr.length);
//        bufferedInputStream.mark(0);
//        bufferedInputStream.read(arr1,0,arr1.length);

        System.err.println(new String(arr)+"  "+new String(arr1));



        inputStream.close();

    }
}
