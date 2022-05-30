package fr.groupeA.famillyBoard.repositories;

import fr.groupeA.famillyBoard.entities.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {}
