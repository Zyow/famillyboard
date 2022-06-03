package fr.groupeA.famillyBoard.controllers;

import fr.groupeA.famillyBoard.entities.FamilyMember;
import fr.groupeA.famillyBoard.entities.Task;
import fr.groupeA.famillyBoard.enums.EnumRole;
import fr.groupeA.famillyBoard.services.FamilyMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/family-members")
public class FamilyMemberController {

    private final FamilyMemberService familyMemberService;

    @Autowired
    public FamilyMemberController(FamilyMemberService familyMemberService) {
        this.familyMemberService = familyMemberService;
    }

    @CrossOrigin(origins = "/*")
    @GetMapping
    public List<FamilyMember> getFamilyMembers() {
        return familyMemberService.getAllFamilyMembers();
    }

    @CrossOrigin(origins = "/*")
    @GetMapping(path = "{familyMemberId}")
    public Optional<FamilyMember> getFamilyMemberById(@PathVariable Long familyMemberId) {
        return familyMemberService.getAFamilyMemberById(familyMemberId);
    }

    @GetMapping( path = "family/{familyId}")
    public List<FamilyMember> getFamilyMemberByFamilyId(@PathVariable Long familyId){
        return familyMemberService.getAFamilyMemberByFamilyId(familyId);
    }

    @CrossOrigin(origins = "/*")
    @PostMapping()
    public FamilyMember createFamilyMember(@RequestBody FamilyMember familyMember){
        return familyMemberService.createOneFamilyMember(familyMember);
    }

    @PostMapping(path = "assignTask")
    public void assignATaskToAFamilyMember(@RequestParam Long memberToAssignId, @RequestBody Task task){
        System.out.println("Passage controller");
        familyMemberService.assignATask(task, memberToAssignId);
    }

    @CrossOrigin(origins = "/*")
    @PutMapping(path = "addMember")
    public void addAUserToAFamily(
            @RequestParam Long familyMemberId,
            @RequestParam Long userId){
        familyMemberService.addAUserToAFamily(familyMemberId, userId);
    }

    @CrossOrigin(origins = "/*")
    @PutMapping(path = "{familyMemberId}")
    public FamilyMember updateFamilyMember(
            @PathVariable Long familyMemberId,
            @RequestBody FamilyMember familyMember, EnumRole role){
        return familyMemberService.updateOneFamilyMember(familyMemberId, familyMember, role);
    }

    @CrossOrigin(origins = "/*")
    @DeleteMapping(path = "{familyMemberId}")
    public void deleteFamilyMember(@PathVariable Long familyMemberId){
        familyMemberService.deleteOneFamilyMemberById(familyMemberId);
    }

    @DeleteMapping(path = "deleteMyFamily/{familyMemberId}")
    public void deleteMyFamily(@PathVariable long familyMemberId){
        familyMemberService.deleteTheFamily(familyMemberId);
    }
}
