package nio;

import okio.Okio;

import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

import static java.lang.System.err;

/**
 * Created by zhuchunliu on 2017/8/16.
 */
public class SocketClientTest {



    private void blockClient() throws Exception{
        SocketChannel channel = SocketChannel.open();
        channel.connect(new InetSocketAddress(5555));

        if (channel.finishConnect()) {
            ByteBuffer buffer = ByteBuffer.allocate(10000);
            channel.read(buffer);
            buffer.flip();
            while (buffer.hasRemaining()) {
                err.println((char) buffer.get());
            }
            channel.close();
        }

    }

    private void unBlockClient() throws Exception{
        SocketChannel channel = SocketChannel.open();
        channel.configureBlocking(false);
        channel.connect(new InetSocketAddress(5555));

        Selector selector = Selector.open();

        SelectionKey key = channel.register(selector,SelectionKey.OP_CONNECT);

        while(true) {
            selector.select();//阻塞，链接上触发

            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext()) {
                this.process(selector,iterator.next());
                iterator.remove();
            }
        }


    }

    private void process(Selector selector,SelectionKey key) throws Exception{
        if(key.isConnectable()){
            SocketChannel socketChannel = (SocketChannel)key.channel();
            ByteBuffer buffer =ByteBuffer.allocate(100).put("client by test".getBytes());
            buffer.flip();
            if (socketChannel.finishConnect()) { //必须加判断，因为是非阻塞，要不会报错
                socketChannel.write(buffer);//写数据，触发server的read事件，进而触发server的write事件
            }
            socketChannel.register(selector,SelectionKey.OP_READ);
        }

        else if(key.isReadable()){
            SocketChannel socketChannel = (SocketChannel)key.channel();
            ByteBuffer buffer = ByteBuffer.allocate(1000);
            socketChannel.read(buffer);
            buffer.flip();
            System.out.print("服务器返回: ");
            while(buffer.hasRemaining()){
                System.out.print((char)buffer.get());
            }
            socketChannel.close();// 要关闭连接，要不server端close连接，会重复read
        }
    }

    public static void main(String[] args) throws Exception {
        SocketClientTest test = new SocketClientTest();
//        test.blockClient();

        test.unBlockClient();
    }
}
