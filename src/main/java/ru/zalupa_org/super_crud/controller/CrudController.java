package ru.zalupa_org.super_crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ru.zalupa_org.super_crud.model.Customer;
import ru.zalupa_org.super_crud.service.CustomerService;
import ru.zalupa_org.super_crud.service.exceptions.UserAlreadyExistException;
import ru.zalupa_org.super_crud.service.exceptions.WrongRoleException;

@Controller
@RequestMapping("/crud")
public class CrudController {

    private final CustomerService customerService;

    @Autowired
    public CrudController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ModelAndView page(@ModelAttribute("c")Customer customer){
        ModelAndView mav = new ModelAndView("crud");
        mav.addObject("customers",customerService.findAll());
        return mav;
    }

    @PostMapping
    public RedirectView addCustomer(@ModelAttribute Customer customer){
        try{
            customerService.addCustomer(customer);
            return new RedirectView("/crud");
        } catch (UserAlreadyExistException | WrongRoleException e){
            return new RedirectView("/crud");
        }
    }

    @GetMapping("/edit/{id}")
    public String editCustomerPage(@PathVariable String id, @ModelAttribute("c") Customer customer){
        customer.setId(Long.parseLong(id));
        return "editCustomer";
    }

    @PostMapping("/edit/{id}")
    public RedirectView editCustomer(@PathVariable String id, @ModelAttribute Customer customer){
        customer.setId(Long.parseLong(id));
        customerService.updateCustomer(customer);
        return new RedirectView("/crud");
    }

    @GetMapping("/delete/{id}")
    public RedirectView deleteCustomer(@PathVariable String id){
        customerService.deleteCustomer(Long.parseLong(id));
        return new RedirectView("/crud");
    }
}
