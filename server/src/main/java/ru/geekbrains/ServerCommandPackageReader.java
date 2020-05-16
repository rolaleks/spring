package ru.geekbrains;

import org.springframework.stereotype.Component;
import ru.geekbrains.net.CommandPackageReader;
import ru.geekbrains.net.CommandPerformable;
import ru.geekbrains.net.NotEnoughBytesException;
import ru.geekbrains.net.PackageCommandType;
import io.netty.buffer.ByteBuf;

@Component("serverCommandPackageReader")
public class ServerCommandPackageReader extends CommandPackageReader {

    protected ClientHandler client;

    public ServerCommandPackageReader(ClientHandler client) {
        this.client = client;
    }

    public boolean read(ByteBuf byteBuf) {

        try {

            /*
             * получаем название команды и ее параметры, если недостаточно байт получено, то будет брошено NotEnoughBytesException
             */
            String name = this.readCommandName(byteBuf);
            String params = this.readParams(byteBuf);

            //Определяем тип команды, и получаем соответсвенный обработчик для этой команды
            CommandPerformable command = client.getServerCommandDispatcher().getCommand(PackageCommandType.valueOf(name));
            command.perform(params);

            return true;

        } catch (NotEnoughBytesException e) {
            return false;
        }
    }


}
