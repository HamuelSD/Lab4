package com.samuel.lab4;

public class EmailFailureException extends Exception {
    public EmailFailureException(String message){
        super("Email Failed!" + message);
    }
}
