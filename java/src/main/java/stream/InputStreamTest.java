package stream;

import java.io.*;

/**
 * Created by zhuchunliu on 2017/2/9.
 */
public class InputStreamTest {

    public static void main(String[] args) throws  Exception{
        File file = new File("/Users/zhuchunliu/test.txt");
        if(!file.exists()){
            file.createNewFile();
        }
        InputStream inputStream = new FileInputStream("/Users/zhuchunliu/test.txt");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String nameLine   = reader.readLine();
        String ageLine    = reader.readLine();
        String emailLine  = reader.readLine();
        String phoneLine  = reader.readLine();
        System.err.println(nameLine+"  "+ageLine+"  "+emailLine+"  "+phoneLine);
        inputStream.skip(0);
        System.err.println(" 字符流 ");

        BufferedInputStream stream = new BufferedInputStream(new FileInputStream("/Users/zhuchunliu/test.txt"));//用上面的inputstream,读取不到数据，流已经读到最后节点了
        byte[] bytes = new byte[20];
        while(stream.read(bytes) != -1){
            System.err.println(new String(bytes)+"==="+bytes.length);
        }


        FileOutputStream out = new FileOutputStream("/Users/zhuchunliu/test.txt",true);
        out.flush();
        BufferedOutputStream outputStream = new BufferedOutputStream(out);
        outputStream.write("tom\n".getBytes());
//        outputStream.write(("20岁\n").getBytes());
//        outputStream.write("tom@qq.com\n".getBytes());
//        outputStream.write("1928495532\n".getBytes());
        outputStream.close(); //close会调用flush方法，强行写入物理文件，要不不会写入文件[除非大于8字节]

        FileOutputStream out1 = new FileOutputStream("/Users/zhuchunliu/test.txt",true);
        out1.write("jack is".getBytes()); // 不用close，事先不会写入缓存，直接写入文件


    }
}
