package ru.geekbrains;

import ru.geekbrains.db.DbConnection;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;


public class CloudServer {

    private DbConnection dbConnection;

    public CloudServer(DbConnection dbConnection) {
        this.setDbConnection(dbConnection);
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            getDbConnection().connect();
            ServerBootstrap b = new ServerBootstrap();
            CloudServer server = this;
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            System.out.println("Новое подключение");
                            ch.pipeline().addLast(new ClientHandler(server, ch));
                        }
                    })
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture f = b.bind(8888).sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public void setDbConnection(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public DbConnection getDbConnection() {
        if (this.dbConnection == null) {
            this.dbConnection = DbConnection.getInstance();
        }
        return dbConnection;
    }
}
