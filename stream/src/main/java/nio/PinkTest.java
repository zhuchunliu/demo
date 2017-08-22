package nio;

import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * Created by zhuchunliu on 2017/8/17.
 */
public class PinkTest {
    public static void main(String[] args) throws Exception{
        Pipe pipe = Pipe.open();
        Pipe.SinkChannel sinkChannel = pipe.sink();
        ByteBuffer buffer = ByteBuffer.allocate(100).put("server".getBytes());
        buffer.flip();
        sinkChannel.write(buffer);

        Pipe.SourceChannel sourceChannel = pipe.source();
        ByteBuffer buffer1 = ByteBuffer.allocate(100);
        sourceChannel.read(buffer1);
        buffer1.flip();
        while (buffer.hasRemaining()){
            System.err.print((char)buffer.get());
        }
    }
}
