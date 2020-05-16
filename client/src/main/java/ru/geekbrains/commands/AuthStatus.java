package ru.geekbrains.commands;

import org.springframework.stereotype.Service;
import ru.geekbrains.ServerHandler;
import ru.geekbrains.net.CommandPerformable;

@Service("authStatus")
public class AuthStatus extends BaseCommand implements CommandPerformable {


    public AuthStatus(ServerHandler serverHandler) {
        super(serverHandler);
    }

    /**
     *
     * @param params 1 - успешная авторизация или 0 - не успешная
     */
    public void perform(String params) {

        boolean isAuth = params.equals("1");

        serverHandler.getCloudClient().setAuth(isAuth);
    }
}
