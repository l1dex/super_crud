package ru.zalupa_org.super_crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    @GetMapping("/")
    public String helloPage(){
        return "hello";
    }
}
