package fr.groupeA.famillyBoard.services;

import fr.groupeA.famillyBoard.entities.Family;
import fr.groupeA.famillyBoard.repositories.FamilyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FamilyService {

    private final FamilyRepository familyRepository;

    public FamilyService(FamilyRepository familyRepository) {
        this.familyRepository = familyRepository;
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
}
