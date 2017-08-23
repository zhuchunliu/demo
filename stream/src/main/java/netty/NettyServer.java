package netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

import java.nio.charset.Charset;

/**
 * Created by zhuchunliu on 2017/8/22.
 */
public class NettyServer {
    private void server() {

        EventLoopGroup bossLoopGroup = new NioEventLoopGroup();//接受客户端连接
        EventLoopGroup workerGroup = new NioEventLoopGroup();//用于socketChannel的网络读写
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossLoopGroup,workerGroup).channel(NioServerSocketChannel.class).
                    option(ChannelOption.SO_BACKLOG,1000).childHandler(new ChildHandler());
            ChannelFuture future = bootstrap.bind(7777).sync();//绑定端口，同步阻塞方法sync等待操作完成

            future.channel().closeFuture().sync();//进行阻塞，等待服务端链路关闭之后main才退出
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            bossLoopGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    private class ChildHandler extends ChannelInitializer {

        @Override
        protected void initChannel(Channel channel) throws Exception {
            channel.pipeline().addLast(new LineBasedFrameDecoder(1024));
//            channel.pipeline().addLast(new StringDecoder());
            channel.pipeline().addLast(new TimeServerHandler());

        }
    }

    private class TimeServerHandler extends ChannelHandlerAdapter{
        private int count;
        @Override //默认接受1024字节，不足则粘包【调用一次channelRead方法】，超过则拆包【拆包后再触发channelRead方法】【不管client.write多少次，只管总字节数】
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ByteBuf buf = (ByteBuf)msg;
            byte[] bytes = new byte[buf.readableBytes()];
            buf.readBytes(bytes);
            System.err.println(bytes.length);
            System.err.println(new String(bytes, Charset.defaultCharset().name()));
            System.err.println(" count: "+(++count));

            ByteBuf bufReponse = Unpooled.copiedBuffer(("server_msg "+System.getProperty("line.separator")).getBytes());
//            ByteBuf bufReponse = Unpooled.copiedBuffer(("server_msg").getBytes());
            ctx.write(bufReponse);
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
            System.err.println("出现异常");
        }
    }

    public static void main(String[] args) throws Exception{
        new NettyServer().server();
    }
}
