package fr.groupeA.famillyBoard.controllers;
import fr.groupeA.famillyBoard.entities.User;
import fr.groupeA.famillyBoard.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping(value= "/*")
public class SignInController {

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    @CrossOrigin(origins = "/*")
    @GetMapping
    public String signIn() {
        return "*";
    }

    @CrossOrigin(origins = "/*")
    @PostMapping
    public String signIn(@Valid User user, HttpServletRequest http) throws ServletException {

        String mdp = user.getPassword();
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        this.userRepository.save(user);
        http.login(user.getEmail(), mdp);
        return "*";
    }
}
