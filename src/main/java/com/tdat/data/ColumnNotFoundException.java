package com.tdat.data;

public class ColumnNotFoundException extends Exception {
    public ColumnNotFoundException(String column){
        super(column + " not found.");
    }
}
