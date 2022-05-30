package fr.groupeA.famillyBoard.services;

import fr.groupeA.famillyBoard.entities.*;
import fr.groupeA.famillyBoard.enums.EnumRole;
import fr.groupeA.famillyBoard.repositories.FamilyMemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FamilyMemberService {

    private final FamilyMemberRepository familyMemberRepository;
    private FamilyService familyService;
    private UserService userService;
    private ScoreService scoreService;
    private TaskService taskService;

    public FamilyMemberService(FamilyMemberRepository familyMemberRepository, FamilyService familyService, UserService userService, ScoreService scoreService, TaskService taskService) {
        this.familyMemberRepository = familyMemberRepository;
        this.familyService = familyService;
        this.userService = userService;
        this.scoreService = scoreService;
        this.taskService = taskService;
    }

    public List<FamilyMember> getAllFamilyMembers() {
        return familyMemberRepository.findAll();
    }

    public Optional<FamilyMember> getAFamilyMemberById(Long id){
        return familyMemberRepository.findById(id);
    }

    public FamilyMember createOneFamilyMember(FamilyMember familyMember) {
        return familyMemberRepository.save(familyMember);
    }

    public FamilyMember updateOneFamilyMember(Long id, FamilyMember familyMember, EnumRole role){
        FamilyMember familyMemberTemp = familyMemberRepository.findById(id).get();
        familyMemberTemp.setRole(role);
        System.out.println("update one family");
    return familyMemberRepository.save(familyMember);
    }

    public void deleteOneFamilyMemberById(Long id) {
        familyMemberRepository.deleteById(id);
    }


    public void addAUserToAFamily(Long memberFamilyId, String userEmail){

        try{
            Optional<FamilyMember> memberFamilyAdmin = familyMemberRepository.findById(memberFamilyId);
            User userToAdd = userService.getUserByEmail(userEmail);
            Optional<Family> family = familyService.getFamilyById(memberFamilyAdmin.get().getFamily().getId());

            EnumRole memberFamilyRole = memberFamilyAdmin.get().getRole();

            if (memberFamilyRole == EnumRole.ADMINISTRATOR){

                // Création d'un score
                Score newScore = scoreService.createAScore(new Score());

                //Création d'un membre de la famille
                FamilyMember familyMember = new FamilyMember();
                familyMember.setUser(userToAdd);
                familyMember.setFamily(family.get());
                familyMember.setScore(newScore);
                familyMember.setRole(EnumRole.USER);

                System.out.println("L'utilisateur " + userToAdd.getFirstName() + " " + userToAdd.getLastName() + " a été ajouté a la famille " + family.get().getTitle() );
                familyMemberRepository.save(familyMember);

            } else {
                System.err.println("Le membre de famille n'a pas les droits d'administrateur.");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}