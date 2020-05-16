package ru.geekbrains;

import org.springframework.stereotype.Component;
import ru.geekbrains.net.CommandPackageReader;
import ru.geekbrains.net.CommandPerformable;
import ru.geekbrains.net.NotEnoughBytesException;
import ru.geekbrains.net.PackageCommandType;
import io.netty.buffer.ByteBuf;

@Component("clientCommandPackageReader")
public class ClientCommandPackageReader extends CommandPackageReader {

    protected ServerHandler serverHandler;

    public ClientCommandPackageReader(ServerHandler serverHandler) {
        this.serverHandler = serverHandler;
    }

    public boolean read(ByteBuf byteBuf) {

        try {

            /*
             * получаем название команды и ее параметры, если недостаточно байт получено, то будет брошено NotEnoughBytesException
             */
            String name = this.readCommandName(byteBuf);
            String params = this.readParams(byteBuf);

            //Определяем тип команды, и получаем соответсвенный обработчик для этой команды
            CommandPerformable command = serverHandler.getClientCommandDispatcher().getCommand(PackageCommandType.valueOf(name));
            command.perform(params);

            return true;

        } catch (NotEnoughBytesException e) {
            return false;
        }
    }


}
