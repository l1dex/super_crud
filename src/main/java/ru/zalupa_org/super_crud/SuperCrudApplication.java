package ru.zalupa_org.super_crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.zalupa_org.super_crud.dao.user.CustomerDAO;
import ru.zalupa_org.super_crud.model.Customer;
import ru.zalupa_org.super_crud.model.Music;

@SpringBootApplication
public class SuperCrudApplication {

	public static void main(String[] args) {

		ApplicationContext context = SpringApplication.run(new Class[]{SuperCrudApplication.class}, args);

		CustomerDAO customerDAO = (CustomerDAO) context.getBean("customerDAO");
		Customer customers = customerDAO.findByIdWithMusicList(1L);

		System.out.println("Login - " + customers.getLogin());

		for(Music music : customers.getMusicList()){
			System.out.println(music.getTitle());
		}

		System.out.println(customers.getRole().getAuthorities());
	}

}
