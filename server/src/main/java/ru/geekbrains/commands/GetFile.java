package ru.geekbrains.commands;

import org.springframework.stereotype.Service;
import ru.geekbrains.ClientHandler;
import ru.geekbrains.db.User;
import ru.geekbrains.net.CommandPerformable;
import ru.geekbrains.net.FilePackage;

@Service("getFile")
public class GetFile  implements CommandPerformable {

    protected ClientHandler clientHandler;

    public GetFile(ClientHandler clientHandler) {

        this.clientHandler = clientHandler;
    }

    /**
     * @param params имя файла, которе следует отправить на клиент
     */
    public void perform(String params) {

        User user = clientHandler.getUser();
        String path = "cloud/" + user.getLogin();
        FilePackage filePackage = new FilePackage(path + "/" + params);
        this.clientHandler.send(filePackage);
    }
}
