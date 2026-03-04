package com.securevault.utils.exceptions;

public class WeakPasswordException extends RuntimeException
{
    public WeakPasswordException() {
        super();
    }
    public WeakPasswordException(String message) {
        super(message);
    }
}
