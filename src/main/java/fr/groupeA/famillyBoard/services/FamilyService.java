package fr.groupeA.famillyBoard.services;

import fr.groupeA.famillyBoard.entities.Family;
import fr.groupeA.famillyBoard.entities.FamilyMember;
import fr.groupeA.famillyBoard.entities.Score;
import fr.groupeA.famillyBoard.entities.User;
import fr.groupeA.famillyBoard.enums.EnumRole;
import fr.groupeA.famillyBoard.repositories.FamilyMemberRepository;
import fr.groupeA.famillyBoard.repositories.FamilyRepository;
import fr.groupeA.famillyBoard.repositories.ScoreRepository;
import fr.groupeA.famillyBoard.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class FamilyService {

    private FamilyRepository familyRepository;
    private UserRepository userRepository;
    private UserService userService;
    private ScoreRepository scoreRepository;
    private FamilyMemberRepository familyMemberRepository;

    public FamilyService(FamilyRepository familyRepository, UserRepository userRepository, UserService userService, ScoreRepository scoreRepository, FamilyMemberRepository familyMemberRepository) {
        this.familyRepository = familyRepository;
        this.userRepository = userRepository;
        this.userService = userService;
        this.scoreRepository = scoreRepository;
        this.familyMemberRepository = familyMemberRepository;
    }

    // récupère la liste des familles
    public List<Family> getFamilies() {
        return familyRepository.findAll();
    }

    // récupère une famille par son id
    public Optional<Family> getFamilyById(Long id){
        return familyRepository.findById(id);
    }

    // création d'une famille
    public Family createFamily(Family family) {
        return familyRepository.save(family);
    }

    // modification d'une famille
    public Family updateFamily(Long id, Family family){
        Family familyTemp = familyRepository.findById(id).get();
        familyTemp.setTitle(family.getTitle());
        return familyRepository.save(familyTemp);
    }

    // suppresion d'une famille
    public void deleteFamilyById(Long id) {
        familyRepository.deleteById(id);
    }

    //Création d'une famille par un utilisateur
    public void userCreateAFamily(Long userId, String familyTitle){

        Optional<User> user = userService.getUserById(userId);

        // Vérification de l'âge de l'utilisateur
        LocalDate today = LocalDate.now();
        LocalDate birthDateUser = user.get().getBirthDate();
        Period p = Period.between(birthDateUser, today);
        System.out.println("Vous avez " + p.getYears() + " ans");

        try{
            if(p.getYears() > 18 ){
                // Création d'une famille
                Family family = new Family();
                family.setTitle(familyTitle);
                Family familyCreated = familyRepository.save(family);
                System.out.println("Creation d'une famille : " + family);

                // Création d'un score
                Score score = new Score();
                score.setScore(0);
                Score scoreCreated = scoreRepository.save(score);
                System.out.println("Creation d'un score : " + score);

                //Création d'un membre de la famille
                FamilyMember familyMember = new FamilyMember();
                familyMember.setUser(user.get());
                familyMember.setScore(scoreCreated);
                familyMember.setRole(EnumRole.ADMINISTRATOR);
                familyMember.setScore(scoreCreated);

                familyMemberRepository.save(familyMember);
                System.out.println("Creation d'un membre d'une famille : " + familyMember);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}