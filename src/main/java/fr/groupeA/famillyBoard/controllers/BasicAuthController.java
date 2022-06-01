package fr.groupeA.famillyBoard.controllers;

import fr.groupeA.famillyBoard.services.AuthenticationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/auth")
public class BasicAuthController {

    @GetMapping(path = "/basicauth")
    public AuthenticationService basicAuth(){
        return new AuthenticationService("Vous êtes authentifié");
    }
}
