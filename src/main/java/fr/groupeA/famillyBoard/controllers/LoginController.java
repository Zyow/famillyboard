package fr.groupeA.famillyBoard.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
public class LoginController {

    @GetMapping
    public String login() {
        return "home";
    }

    @RequestMapping("/*")
    @RolesAllowed("USER")
    public String getUser() {
        return "Welcome, User";
    }

    @RequestMapping("/admin")
    @RolesAllowed("ADMIN")
    public String getAdmin() {
        return "Welcome, Admin";
    }


}
