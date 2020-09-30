package ru.zalupa_org.super_crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.zalupa_org.super_crud.cofig.AppConfig;

@SpringBootApplication
public class SuperCrudApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(new Class[]{SuperCrudApplication.class, AppConfig.class}, args);

	}

}
