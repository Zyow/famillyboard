package fr.groupeA.famillyBoard.services;

import fr.groupeA.famillyBoard.entities.FamilyMember;
import fr.groupeA.famillyBoard.enums.EnumRole;
import fr.groupeA.famillyBoard.repositories.FamilyMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FamilyMemberService {
    private final FamilyMemberRepository familyMemberRepository;

    @Autowired
    public FamilyMemberService(FamilyMemberRepository familyMemberRepository) {
        this.familyMemberRepository = familyMemberRepository;
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
        return familyMemberRepository.save(familyMember);
    }

    public void deleteOneFamilyMemberById(Long id) {
        familyMemberRepository.deleteById(id);
    }
}