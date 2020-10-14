package ru.zalupa_org.super_crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ru.zalupa_org.super_crud.model.Customer;
import ru.zalupa_org.super_crud.service.CustomerService;
import ru.zalupa_org.super_crud.service.exceptions.UserAlreadyExistException;
import ru.zalupa_org.super_crud.service.exceptions.WrongRoleException;

@Controller
@RequestMapping("/reg")
public class RegistrationController {

    private final CustomerService customerService;

    @Autowired
    public RegistrationController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public String regPage(@ModelAttribute("customer") Customer customer){
        return "registration";
    }

    @PostMapping
    public ModelAndView reg(@ModelAttribute Customer customer){
        try {
            customerService.addCustomer(customer);
            System.out.println(customer.getLogin() + " \n" + customer.getPassword() + " \n" + customer.getRole());
            return new ModelAndView("home");
        } catch (UserAlreadyExistException e){
            ModelAndView mav = new ModelAndView("registration");
            mav.addObject("Exist",e.getMessage());
            return mav;
        } catch (WrongRoleException e){
            ModelAndView mav = new ModelAndView("registration");
            mav.addObject("Role",e.getMessage());
            return mav;
        }
    }

}
