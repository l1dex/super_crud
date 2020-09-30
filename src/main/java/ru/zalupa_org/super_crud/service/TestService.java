package ru.zalupa_org.super_crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Service;
import ru.zalupa_org.super_crud.dao.user.CustomerDAO;

@Service
@SpringBootApplication
public class TestService {
    @Autowired
    private CustomerDAO customerDAO;
}
