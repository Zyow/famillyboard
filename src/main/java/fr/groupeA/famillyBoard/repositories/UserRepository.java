package fr.groupeA.famillyBoard.repositories;

import fr.groupeA.famillyBoard.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Period;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
