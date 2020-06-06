package ru.geekbrains.rest;

public class NotFoundException extends RuntimeException {



    public NotFoundException(String err) {
        super(err);
    }

    public NotFoundException() {


    }
}
