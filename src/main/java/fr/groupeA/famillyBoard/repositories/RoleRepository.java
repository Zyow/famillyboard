package fr.groupeA.famillyBoard.repositories;

import fr.groupeA.famillyBoard.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
