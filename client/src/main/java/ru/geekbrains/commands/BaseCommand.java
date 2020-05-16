package ru.geekbrains.commands;

import ru.geekbrains.ServerHandler;

public class BaseCommand {

    protected ServerHandler serverHandler;

    public BaseCommand(ServerHandler serverHandler) {

        this.serverHandler = serverHandler;
    }
}
