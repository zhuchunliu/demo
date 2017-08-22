package nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.util.concurrent.CountDownLatch;

/**
 * Created by zhuchunliu on 2017/8/21.
 */
public class AIOServerTest {

    AsynchronousServerSocketChannel serverSocketChannel = null;
    AsynchronousSocketChannel socketChannel = null;
    CountDownLatch latch = null;

    private void server() throws Exception{
        serverSocketChannel = AsynchronousServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(6666));
        CountDownLatch latch = new CountDownLatch(1);
        serverSocketChannel.accept(new ServerHandler(),new ServerHandler());
        latch.await();
        System.err.println("服务端出现异常");
        serverSocketChannel.close();
    }



    private class ServerHandler implements CompletionHandler<AsynchronousSocketChannel,ServerHandler> {


        @Override
        public void completed(AsynchronousSocketChannel result, ServerHandler attachment) {

            socketChannel = result;
            ByteBuffer buffer = ByteBuffer.allocate(100);
            //不加上此处代码，则只能处理一次请求。需要每次接收到请求，都开启新的监听，可以重复处理客户端请求
            serverSocketChannel.accept(attachment,this);

            // 只要client连接上或者client.write()都会触发回调函数；直接result.read(buffer)，则表示client端刚连上server就立马获取client数据，会获取不到数据
            result.read(buffer,buffer,new ReadHandler());

        }

        @Override
        public void failed(Throwable exc, ServerHandler attachment) {
            latch.countDown();
        }
    }

    public class ReadHandler implements CompletionHandler<Integer,ByteBuffer>{
        @Override
        public void completed(Integer result, ByteBuffer attachment) {

            System.err.print("服务端解析数据");
            attachment.flip();
            byte[] bytes = new byte[attachment.remaining()];
            attachment.get(bytes);
            System.err.println(new String(bytes, Charset.defaultCharset()));

            this.dowrite();
        }

        private void dowrite() {
            ByteBuffer buffer = ByteBuffer.allocate(100);
            buffer.put("server msg".getBytes());
            buffer.flip();
            socketChannel.write(buffer,buffer,new WriteHandler());
        }

        @Override
        public void failed(Throwable exc, ByteBuffer attachment) {
            latch.countDown();
        }

    }

    public class WriteHandler implements CompletionHandler<Integer,ByteBuffer>{
        @Override
        public void completed(Integer result, ByteBuffer attachment) {
            while(attachment.hasRemaining()){
                socketChannel.write(attachment,attachment,this);
            }
        }

        @Override
        public void failed(Throwable exc, ByteBuffer attachment) {
            latch.countDown();
        }

    }



    public static void main(String[] args) throws Exception{
        AIOServerTest serverTest = new AIOServerTest();
        serverTest.server();
    }
}
