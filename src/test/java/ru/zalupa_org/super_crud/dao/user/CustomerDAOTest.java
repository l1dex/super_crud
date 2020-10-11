package ru.zalupa_org.super_crud.dao.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import ru.zalupa_org.super_crud.model.Customer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDAOTest {

    @Autowired
    CustomerDAO customerDAO;

    @Test
    void findByIdWithMusicList() {
        Customer customer = customerDAO.findByIdWithMusicList(1L);


        Assert.notNull(customer.getMusicList(),"message");
    }

    @Test
    void findAllWithMusicList() {
        List<Customer> customerList = new ArrayList<>();
        customerDAO.findAllWithMusicList().forEach(customerList::add);


        Assert.notEmpty(customerList);
    }
}