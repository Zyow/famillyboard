package fr.groupeA.famillyBoard.controllers;

import fr.groupeA.famillyBoard.entities.Family;
import fr.groupeA.famillyBoard.services.FamilyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(name = "/api/families")
public class FamilyController {


    private final FamilyService familyService;

    @Autowired
    public FamilyController(FamilyService familyService) {
        this.familyService = familyService;
    }

    @GetMapping(path = "/all")
    public List<Family> getFamilies() {
      return familyService.getFamilies();
    }

    @GetMapping(path = "{familyId}")
    public Optional<Family> getFamilyById(@PathVariable Long familyId) {
        return familyService.getFamilyById(familyId);
    }

    @PostMapping()
    public Family addFamily(@RequestBody Family family){
        return familyService.createFamily(family);
    }

    @PutMapping(path = "{familyId}")
    public Family updateFamily(@PathVariable Long familyId, @RequestBody Family family){
        return familyService.updateFamily(familyId, family);
    }

    @DeleteMapping(path = "{familyId}")
    public void deleteFamily(@PathVariable Long familyId){
        familyService.deleteFamilyById(familyId);
    }
}
