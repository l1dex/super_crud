package ru.zalupa_org.super_crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.zalupa_org.super_crud.aspect.Monitoring;
import ru.zalupa_org.super_crud.dao.music.MusicDAO;
import ru.zalupa_org.super_crud.dao.user.CustomerDAO;
import ru.zalupa_org.super_crud.model.Customer;
import ru.zalupa_org.super_crud.model.Music;
import ru.zalupa_org.super_crud.model.Role;
import ru.zalupa_org.super_crud.service.exceptions.UserAlreadyExistException;
import ru.zalupa_org.super_crud.service.exceptions.WrongRoleException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private final CustomerDAO customerDAO;
    private final MusicDAO musicDAO;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CustomerService(CustomerDAO customerDAO, PasswordEncoder passwordEncoder, MusicDAO musicDAO) {
        this.customerDAO = customerDAO;
        this.passwordEncoder = passwordEncoder;
        this.musicDAO = musicDAO;
    }

    public void addCustomer(Customer customer) throws UserAlreadyExistException, WrongRoleException {
        if(customerDAO.findByLogin(customer.getLogin()).isPresent()) throw new UserAlreadyExistException("User with this login Already Exist");
        if(!(customer.getRole() == Role.Admin
                || customer.getRole() == Role.Customer
                || customer.getRole() == Role.Moderator))
            throw new WrongRoleException("This role undefined");
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customer.setId(0L);
        customerDAO.save(customer);
    }
    
    public void updateCustomer(Customer customer){
        Customer oldCustomer = customerDAO.findByIdWithMusicList(customer.getId());
        customer.setMusicList(oldCustomer.getMusicList());
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customerDAO.save(customer);
    }

    public Customer findById(int id){
        Optional<Customer> customer = customerDAO.findById((long) id);
        return customer.orElse(null);
    }

    public void deleteCustomer(long id){
        customerDAO.deleteById(id);
    }

    public Customer findByIdWithMusicList(int id){
        Customer customer = customerDAO.findByIdWithMusicList((long) id);
        if(customer != null) return customer;
        else return null;
    }

    public Iterable<Customer> findAll(){
        return customerDAO.findAll();
    }

    public Iterable<Customer> findAllWithMusicList(){
        return customerDAO.findAllWithMusicList();
    }

    @Monitoring
    public Customer findByLogin(String login){
        Optional<Customer> customer = customerDAO.findByLogin(login);
        return customer.orElse(null);
    }

    public void addSong(Music music, Customer customer){
        music.setCustomer(customer);
        music.setId(0L);
        musicDAO.save(music);
    }

    public void editSong(String id, Music music) {
        music.setId(Long.parseLong(id));
        Music musicOld = musicDAO.findById(Long.parseLong(id)).get();
        music.setCustomer(musicOld.getCustomer());
        musicDAO.save(music);
    }

    public void deleteSong(String id) {
        musicDAO.deleteById(Long.parseLong(id));
    }
}
