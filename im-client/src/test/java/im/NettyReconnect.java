//package im;
//
//import io.netty.bootstrap.Bootstrap;
//import io.netty.channel.*;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.socket.SocketChannel;
//import io.netty.channel.socket.nio.NioSocketChannel;
//import io.netty.handler.timeout.IdleStateHandler;
//import pers.enoch.im.client.handler.*;
//
//import java.util.Date;
//import java.util.concurrent.TimeUnit;
//
///**
// * @Author yang.zhao
// * Date: 2020/12/29
// * Description: reconnection
// **/
//public class NettyReconnect {
//    private final String HOST = "127.0.0.1";
//
//    private final int PORT = 9000;
//
//    private final int MAX_RETRY = 5;
//
//    private Bootstrap bootstrap;
//
//    private ChannelFuture future;
//
//    public NettyReconnect(){
//        bootstrap = new Bootstrap();
//        EventLoopGroup workerGroup = new NioEventLoopGroup();
//        bootstrap
//                .group(workerGroup)
//                .channel(NioSocketChannel.class)
//                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
//                .option(ChannelOption.SO_KEEPALIVE, true)
//                .option(ChannelOption.TCP_NODELAY, true)
//                .handler(new ChannelInitializer<SocketChannel>() {
//                    @Override
//                    public void initChannel(SocketChannel ch) {
//                        ChannelPipeline pipeline = ch.pipeline();
//                        pipeline.addLast(new MessageDecoder());
//                        pipeline.addLast(new MessageEncoder());
//                        pipeline.addLast(new IdleStateHandler(0,0,40));
//
//                        pipeline.addLast(new ClientLogicHandler());
//
//                    }
//                });
//
//        doConnect(bootstrap, HOST, PORT, MAX_RETRY);
//    }
//
//    private void doConnect(Bootstrap bootstrap,String host,int port,int retry){
//        future = bootstrap.connect(host,port).addListener(channelFuture -> {
//            if (channelFuture.isSuccess()) {
//                System.out.println(new Date() + ": 连接成功，启动控制台线程……");
//                Channel channel = ((ChannelFuture) future).channel();
//                send(channel);
//            } else if (retry == 0) {
//                System.err.println("重试次数已用完，放弃连接！");
//            } else {
//                // 第几次重连
//                int order = (MAX_RETRY - retry) + 1;
//                // 本次重连的间隔
//                int delay = 1 << order;
//                System.err.println(new Date() + ": 连接失败，第" + order + "次重连……");
//                bootstrap.config().group().schedule(() -> doConnect(bootstrap, host, port, retry - 1), delay, TimeUnit
//                        .SECONDS);
//            }
//        });
//    }
//
//    public boolean checkConnect(){
//        return future != null && future.isSuccess();
//    }
//
//    public <T> void send(T message){
//        Channel channel = future.channel();
//        if(message instanceof Auth.AuthRequest){
//            Auth.AuthRequest request = (Auth.AuthRequest)message;
//            channel.writeAndFlush(request);
//        }else if(message instanceof Single.SingleSendRequest){
//            Single.SingleSendRequest request = (Single.SingleSendRequest)message;
//            channel.writeAndFlush(request);
//        }
//
//    }
//
//    public static void main(String[] args) {
//        NettyReconnect demo = new NettyReconnect();
////        new Thread(()->{
////            NettyReconnect demo = new NettyReconnect();
////            if(!demo.checkConnect()){
////                System.out.println("connect error,please reload");
////                return;
////            }
////            Scanner sc = new Scanner(System.in);
////            final AtomicInteger login = new AtomicInteger(0);
////            final String[] userId = new String[1];
////            while (!Thread.interrupted()){
////                if(login.get() == 0){
////                    System.out.println("输入账号:");
////                    userId[0] = sc.nextLine();
////                    System.out.println("输入token:");
////                    String token = sc.nextLine();
////                    Auth.AuthRequest authRequest = Auth.AuthRequest.newBuilder()
////                            .setUid(userId[0])
////                            .setToken(token)
////                            .build();
////                    demo.send(authRequest);
////                    login.getAndIncrement();
////                }else {
////                    System.out.println("输入发送者id:");
////                    String to = sc.nextLine();
////                    System.out.println("输入发送内容");
////                    String content = sc.nextLine();
////                    Single.SingleSendRequest singleSendRequest = Single.SingleSendRequest.newBuilder()
////                            .setFrom(userId[0])
////                            .setTo(to)
////                            .setContent(content)
////                            .build();
////                    demo.send(singleSendRequest);
////                }
////            }
////        }).start();
//    }
//}
