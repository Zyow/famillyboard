package fr.groupeA.famillyBoard.repositories;

import fr.groupeA.famillyBoard.entities.Family;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyRepository extends JpaRepository<Family, Long> {


}
