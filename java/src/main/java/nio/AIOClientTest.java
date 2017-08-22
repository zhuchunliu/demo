package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

/**
 * Created by zhuchunliu on 2017/8/21.
 */
public class AIOClientTest {

    CountDownLatch latch = null;
    AsynchronousSocketChannel socketChannel = null;
    private void client() throws Exception{

        socketChannel = AsynchronousSocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1",6666),new ClientHandler(),new ClientHandler());
        latch = new CountDownLatch(1);
        latch.await();
        System.err.println("客户端出现异常");
        socketChannel.close();
    }
    CountDownLatch latch = null;
    AsynchronousSocketChannel socketChannel = null;
    private void client() throws Exception{

        socketChannel = AsynchronousSocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1",6666),new ClientHandler(),new ClientHandler());
        latch = new CountDownLatch(1);
        latch.await();
        System.err.println("客户端出现异常");
        socketChannel.close();
    }

    private class ClientHandler implements CompletionHandler<Void, ClientHandler> {

        @Override
        public void completed(Void result, ClientHandler attachment) {
            System.out.println("成功连接上服务器");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.sendMsg();
            ByteBuffer buffer = ByteBuffer.allocate(100);
            socketChannel.read(buffer,buffer,new ReadHandler());
        }

        @Override
        public void failed(Throwable exc, ClientHandler attachment) {
            latch.countDown();
        }

        private void sendMsg(){
            System.err.println("开始发送数据");
            ByteBuffer buffer = ByteBuffer.allocate(100);
            buffer.put("client msg".getBytes());
            buffer.flip();
            socketChannel.write(buffer,buffer,new WriteHander());
        }

    }

    public class ReadHandler implements CompletionHandler<Integer,ByteBuffer>{
        @Override
        public void completed(Integer result, ByteBuffer attachment) {

            System.err.print("客户端解析数据");
            attachment.flip();
            byte[] bytes = new byte[attachment.remaining()];
            attachment.get(bytes);
            System.err.println(new String(bytes, Charset.defaultCharset()));
        }


        @Override
        public void failed(Throwable exc, ByteBuffer attachment) {
            latch.countDown();
        }

    }

    public class WriteHander implements CompletionHandler<Integer,ByteBuffer>{

        @Override
        public void completed(Integer result, ByteBuffer attachment) {
            if(attachment.hasRemaining()){
                socketChannel.write(attachment,attachment,this);
            }
        }

        @Override
        public void failed(Throwable exc, ByteBuffer attachment) {

        }
    }
    private class ClientHandler implements CompletionHandler<Void, ClientHandler> {

        @Override
        public void completed(Void result, ClientHandler attachment) {
            System.out.println("成功连接上服务器");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.sendMsg();
            ByteBuffer buffer = ByteBuffer.allocate(100);
            socketChannel.read(buffer,buffer,new ReadHandler());
        }

        @Override
        public void failed(Throwable exc, ClientHandler attachment) {
            latch.countDown();
        }

        private void sendMsg(){
            System.err.println("开始发送数据");
            ByteBuffer buffer = ByteBuffer.allocate(100);
            buffer.put("client msg".getBytes());
            buffer.flip();
            socketChannel.write(buffer,buffer,new WriteHander());
        }

    }

    public class ReadHandler implements CompletionHandler<Integer,ByteBuffer>{
        @Override
        public void completed(Integer result, ByteBuffer attachment) {

            System.err.print("客户端解析数据");
            attachment.flip();
            byte[] bytes = new byte[attachment.remaining()];
            attachment.get(bytes);
            System.err.println(new String(bytes, Charset.defaultCharset()));
        }


        @Override
        public void failed(Throwable exc, ByteBuffer attachment) {
            latch.countDown();
        }

    }

    public class WriteHander implements CompletionHandler<Integer,ByteBuffer>{

        @Override
        public void completed(Integer result, ByteBuffer attachment) {
            if(attachment.hasRemaining()){
                socketChannel.write(attachment,attachment,this);
            }
        }

        @Override
        public void failed(Throwable exc, ByteBuffer attachment) {

        }
    }

//    public void sendMsg(){
//        ByteBuffer buffer = ByteBuffer.allocate(100);
//        buffer.put("client msg".getBytes());
//        buffer.flip();
//        Future future = socketChannel.write(buffer);
//        System.err.println(future.isDone());
//    }

    public static void main(String[] args) throws Exception{
        AIOClientTest clientTest = new AIOClientTest();
        clientTest.client();
        Thread.sleep(1000);
//        clientTest.sendMsg();
    }
}
