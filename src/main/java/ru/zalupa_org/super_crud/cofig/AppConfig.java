package ru.zalupa_org.super_crud.cofig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("ru.zalupa_org.super_crud")
public class AppConfig {
    @Autowired
    private ApplicationContext applicationContext;
}
