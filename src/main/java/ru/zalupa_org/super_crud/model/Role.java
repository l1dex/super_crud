package ru.zalupa_org.super_crud.model;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public enum Role {
    Customer(new HashSet<>(Arrays.asList(Permission.CUSTOMER_CONTENT, Permission.CUSTOMER_CRUD))),
    Admin(new HashSet<>(Arrays.asList(Permission.CUSTOMER_CRUD))),
    SuperAdmin(new HashSet<>(Arrays.asList(Permission.CUSTOMER_CONTENT, Permission.CUSTOMER_CRUD)));

    private final Set<Permission> permissions;

    Role(Set<Permission> permissions){
        this.permissions = permissions;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getAuthorities(){
        return getPermissions().stream()
                .map(a -> new SimpleGrantedAuthority(a.getPermission()))
                .collect(Collectors.toSet());
    }
}
