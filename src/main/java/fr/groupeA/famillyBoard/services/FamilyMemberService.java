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

    public List<FamilyMember> getAFamilyMemberByFamilyId(long id){ return familyMemberRepository.findFamilyMembersByFamily_Id(id); }
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


    public void addAUserToAFamily(Long memberFamilyId, Long userId){

        try{
            Optional<FamilyMember> memberFamilyAdmin = familyMemberRepository.findById(memberFamilyId);
            Optional<User> userToAdd = userService.getUserById(userId);
            Optional<Family> family = familyService.getFamilyById(memberFamilyAdmin.get().getFamily().getId());

            EnumRole memberFamilyRole = memberFamilyAdmin.get().getRole();

            if (memberFamilyRole == EnumRole.ADMINISTRATOR){

                // Création d'un score
                Score newScore = scoreService.createAScore(new Score());

                //Création d'un membre de la famille
                FamilyMember familyMember = new FamilyMember();
                familyMember.setUser(userToAdd.get());
                familyMember.setFamily(family.get());
                familyMember.setScore(newScore);
                familyMember.setRole(EnumRole.USER);

                System.out.println("L'utilisateur " + userToAdd.get().getFirstName() + " " + userToAdd.get().getLastName() + " a été ajouté a la famille " + family.get().getTitle() );
                familyMemberRepository.save(familyMember);

            } else {
                System.err.println("Le membre de famille n'a pas les droits d'administrateur.");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void assignATask(Task task, Long memberToAssignId){

        Optional<FamilyMember> optionalMemberToAssign = familyMemberRepository.findById(memberToAssignId);
        FamilyMember memberToAssign = optionalMemberToAssign.get();

        Task taskCreated = taskService.createTask(task);

        List<Task> tasksList = memberToAssign.getTasks();
        tasksList.add(taskCreated);
        memberToAssign.setTasks(tasksList);
        familyMemberRepository.save(memberToAssign);
    }

    public void deleteTheFamily(long memberFamilyId) {
        Optional<FamilyMember> familyMember = familyMemberRepository.findById(memberFamilyId);

        if (familyMember.get().getRole() == EnumRole.ADMINISTRATOR) {

            long familyId = familyMember.get().getFamily().getId();
            List<FamilyMember> familyMembersList = familyMemberRepository.findFamilyMembersByFamily_Id(familyMember.get().getFamily().getId());


            for (FamilyMember fM :
                    familyMembersList) {

                Long fMScoreId = fM.getScore().getId();
                Long fMId = fM.getId();

                familyMemberRepository.delete(fM);

                scoreService.deleteScoreById(fMScoreId);
                List<Task> taskList = taskService.getTasksByFamilyMember(fMId);

                for (Task t :
                        taskList) {
                    taskService.deleteTask(t.getId());
                }


            }

            familyService.deleteFamilyById(familyId);
        }
    }
}