package fr.groupeA.famillyBoard.services;

import fr.groupeA.famillyBoard.Methods.AgeCalculator;
import fr.groupeA.famillyBoard.entities.Family;
import fr.groupeA.famillyBoard.entities.FamilyMember;
import fr.groupeA.famillyBoard.entities.Score;
import fr.groupeA.famillyBoard.entities.User;
import fr.groupeA.famillyBoard.enums.EnumRole;
import fr.groupeA.famillyBoard.repositories.UserRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private FamilyService familyService;
    private ScoreService scoreService;
    private FamilyMemberService familyMemberService;

    public UserService(UserRepository userRepository, FamilyService familyService, ScoreService scoreService,@Lazy FamilyMemberService familyMemberService) {
        this.userRepository = userRepository;
        this.familyService = familyService;
        this.scoreService = scoreService;
        this.familyMemberService = familyMemberService;
    }


    public List<User> getUsers(){
        return userRepository.findAll();
    }

     public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
     }

    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
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

    public void userCreateAFamily(Long userId, String familyTitle){

        User userT = userRepository.findById(userId).get();
        int age = AgeCalculator.getYears(userT.getBirthDate());

        try{
            if(age > 18 ){
                System.out.println("A plus de 18 ans");

                // Création d'une famille
                Family family = new Family();
                family.setTitle(familyTitle);
                Family familyCreated = familyService.createFamily(family);
                System.out.println("Creation d'une famille : " + family);

                // Création d'un score
                Score newScore = scoreService.createAScore(new Score());
                System.out.println("Creation d'un Score : " + newScore);

                //Création d'un membre de la famille
                FamilyMember familyMember = new FamilyMember();
                familyMember.setUser(userT);
                familyMember.setFamily(familyCreated);
                familyMember.setScore(newScore);
                familyMember.setRole(EnumRole.ADMINISTRATOR);
                familyMember.setScore(newScore);

                familyMemberService.createOneFamilyMember(familyMember);
                System.out.println("Creation d'un membre d'une famille : " + familyMember);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
