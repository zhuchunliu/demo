package stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;

/**
 * Created by zhuchunliu on 2017/6/2.
 */
public class ObjectOutputStreamTest {

    public static void main(String[] args) throws Exception{
        Student student = new Student(20,"jack");
        File file = new File("/Users/zhuchunliu/abc.txt");
        if(!file.exists()){
            file.createNewFile();
        }
//        FileOutputStream outputStream = new FileOutputStream(file);
//        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
//        objectOutputStream.writeObject(student);
//        objectOutputStream.close();
//        outputStream.close();

        InputStream inputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        Student student1 = (Student) objectInputStream.readObject();
        System.err.println(student1.getAge()+"  "+student1.getName());
    }
}
