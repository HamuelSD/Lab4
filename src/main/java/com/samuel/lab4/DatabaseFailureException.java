package com.samuel.lab4;

public class DatabaseFailureException extends Exception{

    public DatabaseFailureException(String message){
        super("Database Failed!" + message);
    }
}