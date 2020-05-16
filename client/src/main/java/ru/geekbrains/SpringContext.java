package ru.geekbrains;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.geekbrains.commands.AuthStatus;
import ru.geekbrains.commands.GetFiles;

@Configuration
public class SpringContext {


    @Bean(name = "applicationContextProvder")
    public ApplicationContextProvider applicationContextProvider() {
        return new ApplicationContextProvider();
    }

    @Bean(name = "authService")
    @Scope("prototype")
    public AuthStatus authStatus() {
        return new AuthStatus(null);
    }

    @Bean(name = "getFiles")
    @Scope("prototype")
    public GetFiles getFiles() {
        return new GetFiles(null);
    }

    @Bean(name = "clientFilePackageReader")
    @Scope("prototype")
    public ClientFilePackageReader clientFilePackageReader(ServerHandler serverHandler) {
        return new ClientFilePackageReader(serverHandler);
    }

    @Bean(name = "clientCommandPackageReader")
    @Scope("prototype")
    public ClientCommandPackageReader clientCommandPackageReader(ServerHandler serverHandler) {
        return new ClientCommandPackageReader(serverHandler);
    }

    @Bean(name = "serverHandler")
    @Scope("prototype")
    public ServerHandler serverHandler() {
        return new ServerHandler(null, null);
    }
}
