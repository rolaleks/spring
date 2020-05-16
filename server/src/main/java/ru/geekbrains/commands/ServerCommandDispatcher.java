package ru.geekbrains.commands;

import ru.geekbrains.ApplicationContextProvider;
import ru.geekbrains.ClientHandler;
import ru.geekbrains.net.CommandPerformable;
import ru.geekbrains.net.PackageCommandType;

/**
 * Диспечиризация обработчиков команд на сервере и их кеширование
 */
public class ServerCommandDispatcher {

    private ClientHandler clientHandler;
    private Auth auth;
    private GetFile getFile;
    private GetFiles getFiles;

    public ServerCommandDispatcher(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }

    public CommandPerformable getCommand(PackageCommandType type) {

        switch (type) {
            case AUTH:
                return getAuth();
            case GET_FILE:
                return getGetFile();
            case GET_FILES:
                return getGetFiles();
        }

        throw new IllegalStateException("Unknown server command: " + type);
    }

    public GetFile getGetFile() {
        if (getFile == null) {
            getFile = ApplicationContextProvider.getApplicationContext().getBean(GetFile.class, this.clientHandler);
        }
        return getFile;
    }

    public Auth getAuth() {
        if (auth == null) {
            auth = ApplicationContextProvider.getApplicationContext().getBean(Auth.class, this.clientHandler);
        }
        return auth;
    }

    public GetFiles getGetFiles() {
        if (getFiles == null) {
            getFiles = ApplicationContextProvider.getApplicationContext().getBean(GetFiles.class, this.clientHandler);
        }
        return getFiles;
    }
}
