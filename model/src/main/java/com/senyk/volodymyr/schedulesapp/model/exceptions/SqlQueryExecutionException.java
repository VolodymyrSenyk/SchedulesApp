package com.senyk.volodymyr.schedulesapp.model.exceptions;

import java.sql.SQLException;

public class SqlQueryExecutionException extends SQLException {
    public SqlQueryExecutionException() {
        super("Unknown error occurred during SQL query execution");
    }

    public SqlQueryExecutionException(String message) {
        super(message);
    }
}
