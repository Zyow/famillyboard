package fr.groupeA.famillyBoard.dao;

import fr.groupeA.famillyBoard.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
