package fr.groupeA.famillyBoard.controllers;

import fr.groupeA.famillyBoard.entities.Family;
import fr.groupeA.famillyBoard.entities.FamilyMember;
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

    @GetMapping
    public List<FamilyMember> getFamilyMembers() {
        return familyMemberService.getAllFamilyMembers();
    }

    @GetMapping(path = "{familyMemberId}")
    public Optional<FamilyMember> getFamilyMemberById(@PathVariable Long familyMemberId) {
        return familyMemberService.getAFamilyMemberById(familyMemberId);
    }

    @PostMapping()
    public FamilyMember addFamilyMember(@RequestBody FamilyMember familyMember){
        return familyMemberService.createOneFamilyMember(familyMember);
    }

    @PutMapping(path = "{familyMemberId}")
    public FamilyMember updateFamilyMember(@PathVariable Long familyMemberId, @RequestBody FamilyMember familyMember, EnumRole role){
        return familyMemberService.updateOneFamilyMember(familyMemberId, familyMember, role);
    }

    @DeleteMapping(path = "{familyMemberId}")
    public void deleteFamilyMember(@PathVariable Long familyMemberId){
        familyMemberService.deleteOneFamilyMemberById(familyMemberId);
    }
}
