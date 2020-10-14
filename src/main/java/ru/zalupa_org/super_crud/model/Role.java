package ru.zalupa_org.super_crud.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public enum  Role {
    Customer(new HashSet<>(Arrays.asList(Permission.Content))),
    Moderator(new HashSet<>(Arrays.asList(Permission.Crud))),
    Admin(new HashSet<>(Arrays.asList(Permission.Crud,Permission.Content)));

    private Set<Permission> permissionSet;

    Role(Set<Permission> permissionSet) {
        this.permissionSet = permissionSet;
    }

    public Set<Permission> getPermissions() {
        return permissionSet;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
    }
}
