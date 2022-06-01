package fr.groupeA.famillyBoard.controllers;

import fr.groupeA.famillyBoard.entities.User;
import fr.groupeA.famillyBoard.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin(origins = "/*")
    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @CrossOrigin(origins = "/*")
    @GetMapping(path = "{userId}")
    public Optional<User> getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @CrossOrigin(origins = "/*")
    @PostMapping()
    public User createUser(@RequestBody User user){
        return userService.saveUser(user);
    }

    @CrossOrigin(origins = "/*")
    @PutMapping(path = "{userId}")
    public User updateUser(@PathVariable Long userId, @RequestBody User user){
       return userService.updateUser(userId, user);
    }

    @CrossOrigin(origins = "/*")
    @DeleteMapping(path = "{userId}")
    public void deleteUser(@PathVariable Long userId){
        userService.deleteUserById(userId);
    }

    @CrossOrigin(origins = "/*")
    @PostMapping(path = "createMyFamily")
    public void userCreateAFamily(@RequestParam Long userId, @RequestParam String familyTitle){
        userService.userCreateAFamily(userId, familyTitle);
    }

}
