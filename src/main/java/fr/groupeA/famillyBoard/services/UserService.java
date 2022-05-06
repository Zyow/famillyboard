package fr.groupeA.famillyBoard.services;

import fr.groupeA.famillyBoard.entities.Family;
import fr.groupeA.famillyBoard.entities.User;
import fr.groupeA.famillyBoard.repositories.FamilyRepository;
import fr.groupeA.famillyBoard.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

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

        userTemp.setFirstName(user.getFirstName());
        userTemp.setLastName(user.getLastName());
        userTemp.setEmail(user.getEmail());
        userTemp.setBirthDate(user.getBirthDate());
        userTemp.setPhone(user.getPhone());
        userTemp.setPrimaryImage(user.getPrimaryImage());

        return userRepository.save(userTemp);
    }
}
