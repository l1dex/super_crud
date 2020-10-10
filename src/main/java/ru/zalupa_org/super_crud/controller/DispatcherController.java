package ru.zalupa_org.super_crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import ru.zalupa_org.super_crud.rest.AuthenticationRequestDTO;

@Controller("/")
public class DispatcherController {

    @GetMapping("login")
    public String loginPage(@ModelAttribute("dto") AuthenticationRequestDTO dto){
        return "login";
    }
}
