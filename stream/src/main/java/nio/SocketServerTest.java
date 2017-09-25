package nio;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by zhuchunliu on 2017/8/16.
 */
public class SocketServerTest {


    //阻塞
    private void blockServer() throws Exception{
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.bind(new InetSocketAddress(5555));

        SocketChannel socketChannel = serverChannel.accept();

        String str = "123456789abcdefghijklmlopqrstuvwxyz";
        ByteBuffer buffer = ByteBuffer.allocate(str.length());
        buffer.put(str.getBytes());
        buffer.flip();
        socketChannel.write(buffer);
        socketChannel.close();

    }

    private void unBlockserver() throws Exception {

        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        socketChannel.configureBlocking(false); // 设置非阻塞
        socketChannel.bind(new InetSocketAddress(5555));

        Selector selector = Selector.open();
        socketChannel.register(selector, SelectionKey.OP_ACCEPT, "参数"); // ServerSocketChannel唯一可以指定的参数就是OP_ACCEPT
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                selector.wakeup();
//            }
//        }).start();
        this.process(selector);//同步非阻塞【如果一个线程卡主了，另外一个线程请求过来，就会一直等待，因为没有做thread】

        System.err.println("====继续运行");
    }

    public void process(Selector selector) throws Exception{

        while(true) {
            selector.select();//阻塞，除非有socket连接，才会执行

            Set<SelectionKey> set = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            System.err.println("执行+++"+set.size());
            while (iterator.hasNext()) {

                SelectionKey key = iterator.next();
                iterator.remove();

                deal(selector, key);


            }
        }
    }

    private void deal(Selector selector,SelectionKey key) throws Exception{
        if(key.isAcceptable()) {
            ServerSocketChannel channel = (ServerSocketChannel) key.channel();
            SocketChannel socketChannel = channel.accept();
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);//需要客户端发送数据，才会触发
        }
        else if(key.isReadable()) {
            SocketChannel socketChannel = (SocketChannel) key.channel();
            ByteBuffer buffer = ByteBuffer.allocate(1000);
            socketChannel.read(buffer);
            buffer.flip();
            byte[] bytes = new byte[buffer.remaining()];
            buffer.get(bytes);
            System.err.println("客户端请求参数:"+new String(bytes, Charset.defaultCharset().name()));
            if(new String(bytes, Charset.defaultCharset().name()).contains("test")){
                System.err.println("休眠30秒开始");
                Thread.sleep(30000);
                System.err.println("休眠30秒结束");
            }
            buffer.clear();
            socketChannel.register(selector,SelectionKey.OP_WRITE);//即时触发
        }
        else if(key.isWritable()) {
            SocketChannel socketChannel = (SocketChannel) key.channel();

            String str = "server";
            ByteBuffer buffer = ByteBuffer.allocate(str.length());
            buffer.put(str.getBytes());
            buffer.flip();
            socketChannel.write(buffer);
            socketChannel.close();
        }
    }

    public static void main(String[] args) throws Exception{
        SocketServerTest test = new SocketServerTest();
//        test.blockServer();
        test.unBlockserver();
    }
}
