package com.senyk.volodymyr.schedulesapp.model.exceptions;

import java.util.NoSuchElementException;

public class PrivateConstructorEntranceException extends NoSuchElementException {
    public PrivateConstructorEntranceException() {
        super("Entrance to a private constructor");
    }

    public PrivateConstructorEntranceException(String message) {
        super(message);
    }
}
