package ru.geekbrains.net;

public class NotEnoughBytesException extends Exception {
    public NotEnoughBytesException(String str) {
        super(str);
    }
}
