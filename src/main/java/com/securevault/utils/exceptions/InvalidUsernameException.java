package com.securevault.utils.exceptions;

public class InvalidUsernameException extends RuntimeException
{
    public InvalidUsernameException() { super(); }
    public InvalidUsernameException(String message) {
        super(message);
    }
}
