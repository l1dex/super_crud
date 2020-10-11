package ru.zalupa_org.super_crud.model;

public enum Permission {
    Crud("crud_permission"),
    Content("content_permission");

    private String permission;

    Permission(String permission){
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
