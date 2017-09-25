package socket;

import entity.Student;

import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * Created by zhuchunliu on 2017/8/17.
 */
public class CustomerClient {

    class CustomThread implements Runnable{

        @Override
        public void run() {
            try{
                Socket socket = new Socket("localhost",5555);

//                InputStream stream = socket.getInputStream();

//                System.err.println(Thread.currentThread().getName()+" : "+Okio.buffer(Okio.source(stream)).readString(Charset.defaultCharset()));
                ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                Student student = (Student) inputStream.readObject();
                System.err.println(student.getName()+" : "+student.getAge());
            }catch (Exception ex){
                ex.printStackTrace();
            }

        }
    }

    private void customClient() throws Exception{
        new Thread(new CustomThread(),"堵塞线程").start();
    }

    public static void main(String[] args) throws Exception{
        new CustomerClient().customClient();
    }
}
