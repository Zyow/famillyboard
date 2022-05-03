package fr.groupeA.famillyBoard.controllers;

import fr.groupeA.famillyBoard.entities.User;
import fr.groupeA.famillyBoard.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @PostMapping()
    public User createUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    //// TODO: 03/05/2022
    //Erreur sur POSTMAN a corriger
    @PutMapping(path = "{userId}")
    public User updateUser(@PathVariable("userId") long id, @RequestBody User user){
        return userService.saveUser(user);
    }

    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable("userId") long id){
        userService.deleteUserById(id);
    }
}
