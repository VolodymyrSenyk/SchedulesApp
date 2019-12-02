package com.senyk.volodymyr.schedulesapp.view.exceptions;

import java.util.NoSuchElementException;

public class NoSuchDialogFragmentClassException extends NoSuchElementException {
    public NoSuchDialogFragmentClassException() {
        super("This type of the DialogFragment was not found");
    }

    public NoSuchDialogFragmentClassException(String message) {
        super(message);
    }
}
