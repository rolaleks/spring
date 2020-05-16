package ru.geekbrains;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.geekbrains.commands.ClientCommandDispatcher;
import ru.geekbrains.net.Package;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;

import java.io.IOException;

@Component("serverHandler")
public class ServerHandler extends ChannelInboundHandlerAdapter {

    ChannelHandlerContext ctx;
    CloudClient client;
    SocketChannel socket;
    PackageReader packageReader;
    private ClientCommandDispatcher clientCommandDispatcher;


    public ServerHandler(CloudClient client, SocketChannel socket) {
        this.socket = socket;
        this.client = client;
        this.packageReader = new PackageReader(this);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {

        ByteBuf input = (ByteBuf) msg;
        try {
            while (input.readableBytes() > 0) {
                packageReader.read(input);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        this.ctx = ctx;
    }


    public void send(Package pack) {
        if (ctx != null) {
            byte[] bytes;
            while ((bytes = pack.getBytes()).length > 0) {
                ctx.write(Unpooled.copiedBuffer(bytes));
                ctx.flush();
            }
        }
    }

    public CloudClient getCloudClient() {

        return this.client;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
    }

    /**
     * @return Диспечер обработки команд на клиенте
     */
    public ClientCommandDispatcher getClientCommandDispatcher() {

        if (clientCommandDispatcher == null) {
            clientCommandDispatcher = new ClientCommandDispatcher(this);
        }

        return clientCommandDispatcher;
    }
}
