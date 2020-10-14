package ru.zalupa_org.super_crud.service.exceptions;

public class UserAlreadyExistException extends Exception {
    public UserAlreadyExistException(String message){
        super(message);
    }
}
