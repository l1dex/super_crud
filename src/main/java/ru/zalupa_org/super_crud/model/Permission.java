package ru.zalupa_org.super_crud.model;

public enum Permission {
    CUSTOMER_CONTENT("customer:content"),
    CUSTOMER_CRUD("customer:crud");

    private final String permission;

    Permission(String permission){
        this.permission = permission;
    }

    public String getPermission(){
        return permission;
    }
}
