package ru.geekbrains.commands;

import org.springframework.stereotype.Service;
import ru.geekbrains.ClientHandler;
import ru.geekbrains.db.User;
import ru.geekbrains.helpers.FolderReader;
import ru.geekbrains.net.CommandPackage;
import ru.geekbrains.net.CommandPerformable;
import ru.geekbrains.net.PackageCommandType;

@Service("getFiles")
public class GetFiles implements CommandPerformable {

    protected ClientHandler clientHandler;

    public GetFiles(ClientHandler clientHandler) {

        this.clientHandler = clientHandler;
    }

    /**
     * Получение списка файлов на сервере
     * @param
     */
    public void perform(String params) {

        User user = clientHandler.getUser();
        String path = "cloud/" + user.getLogin();
        FolderReader folderReader = new FolderReader(path);
        folderReader.createPath();
        String[] files = folderReader.getFileNames();

        CommandPackage authPackage = new CommandPackage(PackageCommandType.GET_FILES, String.join(";", files));
        this.clientHandler.send(authPackage);
    }
}
