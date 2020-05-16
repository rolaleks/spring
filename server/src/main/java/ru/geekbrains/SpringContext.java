package ru.geekbrains;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.geekbrains.commands.Auth;
import ru.geekbrains.commands.GetFile;
import ru.geekbrains.commands.GetFiles;
import ru.geekbrains.db.DbConnection;

@Configuration
@ComponentScan("ru.geekbrains.postprocessors")
public class SpringContext {


    @Bean(name = "applicationContextProvder")
    public ApplicationContextProvider applicationContextProvider() {
        return new ApplicationContextProvider();
    }

    @Bean(name = "cloudServer")
    public CloudServer cloudServer(DbConnection dbConnection) {
        return new CloudServer(dbConnection);
    }


    @Bean(name = "dbConnection")
    public DbConnection dbConnection() {
        DbConnection dbConnection = new DbConnection();
        dbConnection.setUrl("jdbc:sqlite:DB.db");
        return dbConnection;
    }


    @Bean(name = "auth")
    @Scope("prototype")
    public Auth auth() {
        return new Auth(null);
    }

    @Bean(name = "getFiles")
    @Scope("prototype")
    public GetFiles getFiles() {
        return new GetFiles(null);
    }

    @Bean(name = "getFile")
    @Scope("prototype")
    public GetFile getFile() {
        return new GetFile(null);
    }

    @Bean(name = "serverFilePackageReader")
    @Scope("prototype")
    public ServerFilePackageReader serverFilePackageReader(ClientHandler clientHandler) {
        return new ServerFilePackageReader(clientHandler);
    }

    @Bean(name = "serverCommandPackageReader")
    @Scope("prototype")
    public ServerCommandPackageReader serverCommandPackageReader(ClientHandler clientHandler) {
        return new ServerCommandPackageReader(clientHandler);
    }

    @Bean(name = "clientHandler")
    @Scope("prototype")
    public ClientHandler clientHandler() {
        return new ClientHandler(null, null);
    }
}
