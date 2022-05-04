package fr.groupeA.famillyBoard.services;

import fr.groupeA.famillyBoard.repositories.UserRepository;
import fr.groupeA.famillyBoard.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public void deleteUserById(Long id){
        userRepository.deleteById(id);
    }

    public User updateUser(Long userId, User user) {
        User userTemp = userRepository.findById(userId).get();

        user.setFirstName(user.getFirstName());
        user.setLastName(user.getLastName());
        user.setEmail(user.getEmail());
        user.setBirthDate(user.getBirthDate());
        user.setPhone(user.getPhone());
        user.setPrimaryImage(user.getPrimaryImage());

        return userRepository.save(userTemp);
    }
}
