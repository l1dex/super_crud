package ru.zalupa_org.super_crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zalupa_org.super_crud.dao.user.CustomerDAO;
import ru.zalupa_org.super_crud.model.Customer;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerDAO customerDAO;

    public List<Customer> getAll(){
        List<Customer> customerList = new ArrayList<>();
        customerDAO.findAllWithMusicList().forEach(customerList::add);
        return customerList;
    }
}
