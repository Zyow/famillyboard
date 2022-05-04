package fr.groupeA.famillyBoard.services;

import fr.groupeA.famillyBoard.entities.User;
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

        userTemp.setFirstName(user.getFirstName());
        userTemp.setLastName(user.getLastName());
        userTemp.setEmail(user.getEmail());
        userTemp.setBirthDate(user.getBirthDate());
        userTemp.setPhone(user.getPhone());
        userTemp.setPrimaryImage(user.getPrimaryImage());

        return userRepository.save(userTemp);
    }

    //Création d'une famille par un utilisateur
    public void userCreateFamily(Long userId){

        Optional<User> user = userRepository.findById(userId);

        LocalDate today = LocalDate.now();
        LocalDate birthDateUser = user.get().getBirthDate();
        Period p = Period.between(birthDateUser, today);
        System.out.println("Vous avez " + p.getYears() + " ans");

        try{
            if(p.getYears() > 18 ){
                System.out.println("Création d'une famile");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
