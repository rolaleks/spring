package ru.geekbrains.commands;

import org.springframework.stereotype.Service;
import ru.geekbrains.ControllerManager;
import ru.geekbrains.ServerHandler;
import ru.geekbrains.net.CommandPerformable;

@Service("getFiles")
public class GetFiles extends BaseCommand implements CommandPerformable {

    public GetFiles(ServerHandler serverHandler) {
        super(serverHandler);
    }

    /**
     *
     * @param params список файлов с сервера разделенные ";"
     */
    public void perform(String params) {

        String[] files = params.split(";");
        ControllerManager.getMainController().setRemoteFiles(files);
    }
}
