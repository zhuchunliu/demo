package netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

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
        protected void initChannel(Channel channel) throws Exception {
            channel.pipeline().addLast(new LineBasedFrameDecoder(1024));//\r\n或\r分隔符；write数据需要加分隔符
//            channel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, Unpooled.copiedBuffer("$$".getBytes())));
//            channel.pipeline().addFirst(new FixedLengthFrameDecoder(5));
            channel.pipeline().addLast(new StringDecoder()); // channelRead时候，将msg直接转换成String，不用通过ByteBuf转换
            channel.pipeline().addLast(new TimeClientHandler());
        }
    }

    private class TimeClientHandler extends ChannelHandlerAdapter{
        int count = 0;
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            for(int i=0;i<2;i++) {//重复发送999次，会使server端出现拆包，粘包问题

                ByteBuf buf = Unpooled.copiedBuffer(("client_msg"+System.getProperty("line.separator")).getBytes()); // LineBasedFrameDecoder需要
//                ByteBuf buf = Unpooled.copiedBuffer(("client_msg"+"$$").getBytes()); // DelimiterBasedFrameDecoder需要
//                ByteBuf buf = Unpooled.copiedBuffer(("client_msg").getBytes());
                ctx.writeAndFlush(buf);

            }

        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//            ByteBuf buf = (ByteBuf)msg;
//            byte[] bytes = new byte[buf.readableBytes()];
//            buf.readBytes(bytes);
//            System.err.print(new String(bytes, Charset.defaultCharset().name())+" count: "+(++count));

            System.err.println((String)msg+" count: "+(++count));
//            ctx.writeAndFlush(Unpooled.copiedBuffer(("").getBytes()));

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
