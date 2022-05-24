package fr.groupeA.famillyBoard.repositories;

import fr.groupeA.famillyBoard.entities.FamilyMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FamilyMemberRepository extends JpaRepository<FamilyMember, Long> {

}
