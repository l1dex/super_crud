package ru.zalupa_org.super_crud.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.zalupa_org.super_crud.dao.user.CustomerDAO;
import ru.zalupa_org.super_crud.model.Customer;

import java.util.Optional;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {


    private final CustomerDAO customerDAO;

    @Autowired
    public UserDetailsServiceImpl(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Customer> customer = customerDAO.findByLogin(username);
        if(!customer.isPresent()) throw new UsernameNotFoundException("User doesnt exist");
        return SecurityUser.fromCustomer(customer.get());
    }
}
