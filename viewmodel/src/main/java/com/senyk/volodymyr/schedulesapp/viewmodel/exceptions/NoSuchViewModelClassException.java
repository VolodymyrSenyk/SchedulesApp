package com.senyk.volodymyr.schedulesapp.viewmodel.exceptions;

import java.util.NoSuchElementException;

public class NoSuchViewModelClassException extends NoSuchElementException {
    public NoSuchViewModelClassException() {
        super("This type of the ViewModel was not found");
    }

    public NoSuchViewModelClassException(String message) {
        super(message);
    }
}
