package com.senyk.volodymyr.schedulesapp.model.exceptions;

public class SQLiteQueryExecutingException extends RuntimeException {
    public SQLiteQueryExecutingException() {
        super("An error occurred during query execution");
    }

    public SQLiteQueryExecutingException(String message) {
        super(message);
    }
}
