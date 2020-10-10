package ru.zalupa_org.super_crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.zalupa_org.super_crud.dao.user.CustomerDAO;
import ru.zalupa_org.super_crud.model.Customer;
import ru.zalupa_org.super_crud.model.Role;

import javax.annotation.PostConstruct;
import java.util.Collections;

@SpringBootApplication
public class SuperCrudApplication {
	@Autowired
	private CustomerDAO customerDAO;

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(new Class[]{SuperCrudApplication.class}, args);

//		CustomerDAO customerDAO = (CustomerDAO) context.getBean("customerDAO");
//		Customer customers = customerDAO.findByIdWithMusicList(1L);
//
//		System.out.println("Login - " + customers.getLogin());
//
//		for(Music music : customers.getMusicList()){
//			System.out.println(music.getTitle());
//		}
//
//		System.out.println(customers.getRole().getAuthorities());
	}

	@PostConstruct
	public void init(){
		customerDAO.save(new Customer("test","test", Collections.emptyList(), Role.SuperAdmin));
	}

}
