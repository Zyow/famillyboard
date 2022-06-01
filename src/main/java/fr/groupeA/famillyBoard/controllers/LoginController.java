//package fr.groupeA.famillyBoard.controllers;
//
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.annotation.security.RolesAllowed;
//
//@RestController
//@RequestMapping(path = "api/login")
//public class LoginController {
//
//    @CrossOrigin(origins = "/*")
//    @GetMapping
//    public String login() {
//        return "home";
//    }
//
//    @CrossOrigin(origins = "/*")
//    @RequestMapping("/user")
//    @RolesAllowed("USER")
//    public String getUser() {
//        return "Welcome, User";
//    }
//
//    @CrossOrigin(origins = "/*")
//    @RequestMapping("/admin")
//    @RolesAllowed("ADMIN")
//    public String getAdmin() {
//        return "Welcome, Admin";
//    }
//
//
//}
