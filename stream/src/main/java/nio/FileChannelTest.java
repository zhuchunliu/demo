package nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by zhuchunliu on 2017/8/14.
 */
public class FileChannelTest {
    private RandomAccessFile file = null;
    public FileChannelTest(RandomAccessFile file){
        this.file = file;
    }

    private void readOne() throws Exception{

        FileChannel channel = file.getChannel();
        ByteBuffer  buffer = ByteBuffer.allocate(10);
//        channel.position(20);
        int read = channel.read(buffer);

        while (read != -1) {
            System.out.println(" read " + read+"  "+channel.position());
            buffer.flip();
            while (buffer.hasRemaining()) {//判断是否有未读数据

                byte[] bb = new byte[buffer.limit()];
                buffer.get(bb,0,buffer.limit());
                System.err.println(new String(bb)+ "++++"+buffer.hasRemaining());

            }
            buffer.compact(); //清除已经读取的缓存数据，设置position,limit参数
            read = channel.read(buffer);
            System.out.println(" read " + read+"=====");
        }
        file .close();
    }


    private void readTwo() throws Exception{
        FileChannel channel = file.getChannel();
        ByteBuffer  buffer = ByteBuffer.allocate(100);
        int read = channel.read(buffer);
        while (read != -1) {
            System.out.println(" read " + read);
            buffer.flip();
            int index =0,rindex=0;
            while (buffer.hasRemaining()) {

                if(index++ == 3){
                    buffer.mark();
//                    System.err.println();
                }

                if(rindex++ ==5) {
                    buffer.reset();
                    System.err.println();
                }
                System.err.println((char)buffer.get()+ "++++"+buffer.hasRemaining());

            }

            buffer.compact();
            read = channel.read(buffer);
            System.out.println(" read " + read+"=====");
        }
        file .close();
    }

    private void writeone() throws Exception{

        FileChannel channel = file.getChannel();
        String str = "123456789abcdefghijklmlopqrstuvwxyz";
        ByteBuffer buffer = ByteBuffer.allocate(str.length());
        buffer.put(str.getBytes());
        buffer.flip();
        channel.position(100);
        while(buffer.hasRemaining()){
            System.err.println("+++");
            channel.write(buffer);
        }
        channel.force(true);
        System.err.println("   "+channel.size()+"   "+file.length());
        file.close();
    }

    private void trans(){
        String str = "123456789abcdefghijklmlopqrstuvwxyz";
        ByteBuffer buffer = ByteBuffer.allocate(str.length());

    }


    public static void main(String[] args) throws Exception{
        RandomAccessFile readFile = new RandomAccessFile("/Users/zhuchunliu/test.txt","rw");
        FileChannelTest test = new FileChannelTest(readFile);
//        test.readOne();
//        test.readTwo();

        RandomAccessFile writeFile = new RandomAccessFile("/Users/zhuchunliu/test_write.txt","rw");
        test = new FileChannelTest(writeFile);
        test.writeone();
    }
}
