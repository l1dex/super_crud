package ru.zalupa_org.super_crud.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.zalupa_org.super_crud.dao.user.CustomerDAO;
import ru.zalupa_org.super_crud.model.Customer;

@Component("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final CustomerDAO customerDAO;

    @Autowired
    public UserDetailsServiceImpl(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Customer user = customerDAO.findByLogin(login).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exists"));
        return SecurityUser.fromUser(user);
    }
}
