package ru.zalupa_org.super_crud.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import ru.zalupa_org.super_crud.model.Customer;

import java.util.Collection;
import java.util.List;

@Data
public class SecurityUser implements UserDetails {
    private String username;
    private String password;
    private List<SimpleGrantedAuthority> authorityList;
    private boolean isActive;

    public SecurityUser(String username, String password, List<SimpleGrantedAuthority> authorityList, boolean isActive) {
        this.username = username;
        this.password = password;
        this.authorityList = authorityList;
        this.isActive = isActive;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorityList;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isActive;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isActive;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

    public static UserDetails fromCustomer(Customer customer){
        return new User(customer.getLogin(),
                customer.getPassword(),
                true,
                true,
                true,
                true,
                customer.getRole().getAuthorities());
    }
}
