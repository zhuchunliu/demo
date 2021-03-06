package netty;

import entity.Student;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * Created by zhuchunliu on 2017/8/22.
 */
public class NettyServer {
    private void server() {

        EventLoopGroup bossLoopGroup = new NioEventLoopGroup();//接受客户端连接
        EventLoopGroup workerGroup = new NioEventLoopGroup();//用于socketChannel的网络读写
        try {
            //BACKLOG用于构造服务端套接字ServerSocket对象，标识当服务器请求处理线程全满时，用于临时存放已完成三次握手的请求的队列的最大长度。
            // 如果未设置或所设置的值小于1，Java将使用默认值50。
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossLoopGroup,workerGroup).channel(NioServerSocketChannel.class).
                    option(ChannelOption.SO_BACKLOG,1000).childHandler(new ChildHandler());
            ChannelFuture future = bootstrap.bind(7777).awaitUninterruptibly();//绑定端口，同步阻塞方法sync等待操作完成

            future.channel().closeFuture().awaitUninterruptibly();//进行阻塞，等待服务端链路关闭之后main才退出
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            bossLoopGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    private class ChildHandler extends ChannelInitializer {

        @Override
        protected void initChannel(Channel channel) throws Exception {
            channel.pipeline().addLast(new LineBasedFrameDecoder(1024));//\r\n或\r分隔符；write数据需要加分隔符
//            channel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, Unpooled.copiedBuffer("$$".getBytes())));//定义DelimiterBasedFrameDecoder分隔符为$$
//            channel.pipeline().addLast(new FixedLengthFrameDecoder(18));
            channel.pipeline().addLast(new StringDecoder()); // channelRead时候，将msg直接转换成String，不用通过ByteBuf转换
            channel.pipeline().addLast(new TimeServerHandler());
        }
    }

    private class TimeServerHandler extends ChannelHandlerAdapter{
        private int count;
        @Override //默认接受1024字节，不足则粘包【调用一次channelRead方法】，超过则拆包【拆包后再触发channelRead方法】【不管client.write多少次，只管总字节数】
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//            ByteBuf buf = (ByteBuf)msg;
//            byte[] bytes = new byte[buf.readableBytes()];
//            buf.readBytes(bytes);
//            System.err.print(new String(bytes, Charset.defaultCharset().name())+" count: "+(++count));

            System.err.println((String)msg+" count: "+(++count));

            ByteBuf bufReponse = Unpooled.copiedBuffer(("server_msg "+System.getProperty("line.separator")).getBytes()); //// LineBasedFrameDecoder需要
//            ByteBuf bufReponse = Unpooled.copiedBuffer(("server_msg "+"$$").getBytes()); //// DelimiterBasedFrameDecoder需要
//            ByteBuf bufReponse = Unpooled.copiedBuffer(("server_msg").getBytes());
            ctx.write(bufReponse);// (重要) 一旦调用write方法，后续的所有handler都将不再执行
//            ctx.write(bufReponse);
            System.err.println("========");
        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
//            System.out.println("读取就绪");
            //从性能角度考虑，为了防止频繁的唤醒selector进行消息发送，
            // netty.write方法不直接写入socketChannel中，而是发送缓存数组中
            // 调用flush方法将缓存区中的数据写入socketChannel
            ctx.flush();
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            ctx.close();
            System.err.println("出现异常"+cause.getMessage());
        }
    }

    public static void main(String[] args) throws Exception{
        new NettyServer().server();
    }
}
