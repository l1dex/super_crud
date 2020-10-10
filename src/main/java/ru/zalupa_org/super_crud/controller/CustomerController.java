package ru.zalupa_org.super_crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.zalupa_org.super_crud.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/list")
    @PreAuthorize("hasAnyAuthority('customer:crud')")
    public ModelAndView customerPage(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("customers",customerService.getAll());
        mav.setViewName("customers.html");
        return mav;
    }

}
