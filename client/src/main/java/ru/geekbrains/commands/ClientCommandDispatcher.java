package ru.geekbrains.commands;

import ru.geekbrains.ApplicationContextProvider;
import ru.geekbrains.ClientFilePackageReader;
import ru.geekbrains.ServerHandler;
import ru.geekbrains.net.CommandPerformable;
import ru.geekbrains.net.PackageCommandType;

/**
 * Диспечиризация обработчиков команд на клиенте и их кеширование
 */
public class ClientCommandDispatcher {

    private ServerHandler serverHandler;
    private AuthStatus authStatus;
    private GetFiles getFiles;

    public ClientCommandDispatcher(ServerHandler serverHandler) {
        this.serverHandler = serverHandler;
    }

    public CommandPerformable getCommand(PackageCommandType type) {

        switch (type) {
            case AUTH:
                return getAuthStatus();
            case GET_FILES:
                return getGetFiles();
        }

        throw new IllegalStateException("Unknown client command: " + type);
    }

    public AuthStatus getAuthStatus() {
        if (authStatus == null) {
            authStatus = ApplicationContextProvider.getApplicationContext().getBean(AuthStatus.class, this.serverHandler);
        }
        return authStatus;
    }

    public GetFiles getGetFiles() {
        if (getFiles == null) {
            getFiles = ApplicationContextProvider.getApplicationContext().getBean(GetFiles.class, this.serverHandler);
        }
        return getFiles;
    }
}
