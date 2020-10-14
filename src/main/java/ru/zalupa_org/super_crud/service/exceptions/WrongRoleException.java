package ru.zalupa_org.super_crud.service.exceptions;

public class WrongRoleException extends Exception {
    public WrongRoleException(String mes){
        super(mes);
    }
}
