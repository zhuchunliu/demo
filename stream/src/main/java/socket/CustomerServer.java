package socket;

import entity.Student;

import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by zhuchunliu on 2017/8/24.
 */
public class CustomerServer {
    boolean flag = true;
    private void customServer() throws Exception{

        ServerSocket serverSocket = new ServerSocket(5555);
        while (true) {
            Socket socket = serverSocket.accept();
//            this.sync(socket);
            this.unSync(socket);

//            serverSocket.close();
        }
    }

    public void sync(Socket socket) throws Exception{
        String str = "123456789abcdefghijklmlopqrstuvwxyz";
        socket.getOutputStream().write(str.getBytes());
        if(flag) {
            flag = false;
            Thread.sleep(10000);//能够阻塞后面的socket请求
        }
        socket.close();
    }

    public void unSync(Socket socket) throws Exception{
        String str = "123456789abcdefghijklmlopqrstuvwxyz";
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
//                    socket.getOutputStream().write(str.getBytes());
                    ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
                    outputStream.writeObject(new Student(12,"张三"));
                    if(flag) {
                        flag = false;
                        Thread.sleep(10000);//不会阻塞后面的socket请求
                    }
                    socket.close();
                }catch (Exception ex){
                    ex.printStackTrace();
                }

            }
        }).start();

    }

    public static void main(String[] args) throws Exception{
        new CustomerServer().customServer();
    }
}
