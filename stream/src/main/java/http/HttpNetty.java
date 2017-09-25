package http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.stream.ChunkedWriteHandler;
import netty.NettyServer;

import java.nio.charset.Charset;

/**
 * Created by zhuchunliu on 2017/8/30.
 */
public class HttpNetty {
    public void run(){
        EventLoopGroup bossLoopGroup = new NioEventLoopGroup();//接受客户端连接
        EventLoopGroup workerGroup = new NioEventLoopGroup();//用于socketChannel的网络读写
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossLoopGroup,workerGroup).channel(NioServerSocketChannel.class).
                    option(ChannelOption.SO_BACKLOG,1000).childHandler(new ChildHandler());
            ChannelFuture future = bootstrap.bind(9999).sync();//绑定端口，同步阻塞方法sync等待操作完成

            future.channel().closeFuture().sync();//进行阻塞，等待服务端链路关闭之后main才退出
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            bossLoopGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }

    public class ChildHandler extends ChannelInitializer {

        @Override
        protected void initChannel(Channel ch){
//            ch.pipeline().addLast(new HttpRequestDecoder());//解码器
            //作用：将多个单一消息转换成单一的FullHttpRequest或者FullHttpResponse
            ch.pipeline().addLast(new HttpObjectAggregator(65536));
            ch.pipeline().addLast(new HttpResponseEncoder());//对http响应消息进行编码
            //支持异步发送放大码流，但不占用过多的内存，防止java泄露
            ch.pipeline().addLast(new ChunkedWriteHandler());
            ch.pipeline().addLast(new HttpFileHandler());
        }
    }

    public class HttpFileHandler extends ChannelHandlerAdapter{


        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            if(msg instanceof HttpRequest){
                HttpRequest request = (HttpRequest)msg;
                System.err.println(request.headers().get("messageType")+"  "+request.uri());
            }
            if(msg instanceof HttpContent){
                HttpContent content = (HttpContent)msg;
                ByteBuf buf = content.content();
                System.err.print(buf.toString(Charset.defaultCharset()));
                buf.release();

                FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_0, HttpResponseStatus.OK,Unpooled.wrappedBuffer("test 测试".getBytes()));
                response.headers().add("Content-Type", "text/html");
//                response.content().writeBytes("test".getBytes());
                ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
            }



        }

        @Override
        public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
            ctx.flush();
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            cause.printStackTrace();
            ctx.close();
        }
    }

    public static void main(String[] args) {
        new HttpNetty().run();
    }
}
