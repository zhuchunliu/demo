package nio;

import okio.Okio;

import java.io.InputStream;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * Created by zhuchunliu on 2017/8/17.
 */
public class SocketClientTestCopy{
    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("localhost",5555);
        InputStream stream = socket.getInputStream();

        System.err.println(Thread.currentThread().getName()+" : "+ Okio.buffer(Okio.source(stream)).readString(Charset.defaultCharset()));

    }
}
