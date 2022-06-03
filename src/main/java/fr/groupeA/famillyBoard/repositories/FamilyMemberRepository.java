package fr.groupeA.famillyBoard.repositories;

import fr.groupeA.famillyBoard.entities.FamilyMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface FamilyMemberRepository extends JpaRepository<FamilyMember, Long> {
    List<FamilyMember> findFamilyMembersByFamily_Id(long id);
}
