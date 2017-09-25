package msgpack;

import entity.Score;
import entity.Student;
import entity.Teacher;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhuchunliu on 2017/8/22.
 */
public class NettyMsgPackClient {
    private void client() {
        EventLoopGroup loopGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(loopGroup).channel(NioSocketChannel.class).
                    option(ChannelOption.TCP_NODELAY,Boolean.TRUE).handler(new ClientHandler());
            ChannelFuture future = bootstrap.connect("localhost",8888).sync();
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
            channel.pipeline().addLast(new MsgpackEncoder());
            channel.pipeline().addLast(new MsgPackDecoder());
            channel.pipeline().addLast(new TimeClientHandler());
        }
    }

    private class TimeClientHandler extends ChannelHandlerAdapter{
        int count = 0;
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            List<Score> list = new ArrayList<Score>();
            list.add(new Score(10,"语文"));
            list.add(new Score(12,"数学"));
            for(Score score:list){
                ctx.write(score);
            }
            ctx.flush();
            System.err.println("=====");

        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            ctx.close();
        }
    }

    public static void main(String[] args) {
        new NettyMsgPackClient().client();
    }
}
