package ru.zalupa_org.super_crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ru.zalupa_org.super_crud.aspect.Monitoring;
import ru.zalupa_org.super_crud.model.Customer;
import ru.zalupa_org.super_crud.model.Music;
import ru.zalupa_org.super_crud.service.CustomerService;

@Controller
@RequestMapping("/content")
public class ContentController {

    private final CustomerService customerService;

    @Autowired
    public ContentController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Monitoring
    @GetMapping
    public ModelAndView contentPage(@ModelAttribute("m") Music music){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Customer customer = customerService.findByLogin(authentication.getName());
        ModelAndView mav = new ModelAndView("content");
        mav.addObject("musicList",customer.getMusicList());
        return mav;
    }

    @PostMapping
    public RedirectView addNewSong(@ModelAttribute("m") Music m){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Customer customer = customerService.findByLogin(authentication.getName());
        customerService.addSong(m,customer);
        return new RedirectView("/content");
    }


    @GetMapping("/edit/{id}")
    public String editSongPage(@PathVariable String id, @ModelAttribute("music") Music music){
        music.setId(Long.parseLong(id));
        return "/editSong";
    }

    @PostMapping("/edit/{id}")
    public RedirectView editSong(@PathVariable String id, @ModelAttribute("m") Music music){
        customerService.editSong(id,music);
        return new RedirectView("/content");
    }

    @GetMapping("/delete/{id}")
    public RedirectView deleteSong(@PathVariable String id){
        customerService.deleteSong(id);
        return new RedirectView("/content");
    }
}
