package netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

import java.nio.charset.Charset;

/**
 * Created by zhuchunliu on 2017/8/22.
 */
public class NettyClient {
    private void client() {
        EventLoopGroup loopGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(loopGroup).channel(NioSocketChannel.class).
                    option(ChannelOption.TCP_NODELAY,Boolean.TRUE).handler(new ClientHandler());
            ChannelFuture future = bootstrap.connect("localhost",7777).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            loopGroup.shutdownGracefully();
        }

    }

    private class ClientHandler extends ChannelInitializer {

        @Override
        protected void initChannel(Channel ch) throws Exception {
            ch.pipeline().addLast(new LineBasedFrameDecoder(1024));
//            ch.pipeline().addLast(new StringDecoder());
            ch.pipeline().addLast(new TimeClientHandler());
        }
    }

    private class TimeClientHandler extends ChannelHandlerAdapter{
        int count = 0;
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            for(int i=0;i<999;i++) {//重复发送999次，会使server端出现拆包，粘包问题
                ByteBuf buf = Unpooled.copiedBuffer(("client_msg"+System.getProperty("line.separator")).getBytes());
//                ByteBuf buf = Unpooled.copiedBuffer(("client_msg").getBytes());
                ctx.writeAndFlush(buf);
            }
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ByteBuf buf = (ByteBuf)msg;
            byte[] bytes = new byte[buf.readableBytes()];
            buf.readBytes(bytes);
            System.err.println(new String(bytes, Charset.defaultCharset().name()));
            System.err.print(msg);
            System.err.println(" count: "+(++count));

        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            ctx.close();
        }
    }

    public static void main(String[] args) {
        new NettyClient().client();
    }
}
