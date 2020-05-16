package ru.geekbrains;

import org.springframework.stereotype.Component;
import ru.geekbrains.net.FilePackageReader;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component("serverFilePackageReader")
public class ServerFilePackageReader extends FilePackageReader {

    protected ClientHandler client;

    public ServerFilePackageReader(ClientHandler client) {
        this.client = client;
    }

    public Path getFilePath(String fileName) {

        return Paths.get("cloud/" + client.getUser().getLogin() + "/" + fileName);
    }
}
