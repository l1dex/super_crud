package ru.zalupa_org.super_crud.rest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;
import ru.zalupa_org.super_crud.dao.user.CustomerDAO;
import ru.zalupa_org.super_crud.model.Customer;
import ru.zalupa_org.super_crud.security.JwtTokenProvider;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationRestController {

    private final AuthenticationManager authenticationManager;
    private CustomerDAO customerDAO;
    private JwtTokenProvider jwtTokenProvider;

    public AuthenticationRestController(AuthenticationManager authenticationManager, CustomerDAO customerDAO, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.customerDAO = customerDAO;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@ModelAttribute AuthenticationRequestDTO request, HttpServletResponse httpServletResponse){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getLogin(),request.getPassword()));
            Customer customer = customerDAO.findByLogin(request.getLogin()).get();
            String token = jwtTokenProvider.createToken(request.getLogin(),customer.getRole().name());

            String favColour = "steelblue";
            ResponseCookie cookie = ResponseCookie.from("fav-col", favColour).build();

            ResponseEntity<Object> build = ResponseEntity.ok()
                    .header(HttpHeaders.SET_COOKIE, cookie.toString())
                    .body("redirect:/customers/list");
            return build;

        } catch (AuthenticationException e){
            return new ResponseEntity<Object>(null);
        }
    }

    @GetMapping("/login")
    public ModelAndView loginPage(@ModelAttribute("dto") AuthenticationRequestDTO dto){
        return new ModelAndView( "login");
    }

    @GetMapping("/logout")
    public void authenticate(HttpServletRequest request, HttpServletResponse response){
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request,response,null);
    }
}
