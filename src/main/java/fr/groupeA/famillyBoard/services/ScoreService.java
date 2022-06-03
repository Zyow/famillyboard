package fr.groupeA.famillyBoard.services;

import fr.groupeA.famillyBoard.entities.Score;
import fr.groupeA.famillyBoard.repositories.ScoreRepository;
import org.springframework.stereotype.Service;

@Service
public class ScoreService {

    private final ScoreRepository scoreRepository;

    public ScoreService(ScoreRepository scoreRepository) {
        this.scoreRepository = scoreRepository;
    }

    public Score createAScore(Score score) {

        score.setScore(0);
        System.out.println("Creation d'un score : " + score);

        return scoreRepository.save(score);
    }

    public void deleteScoreById(Long id){
        scoreRepository.deleteById(id);
    }
}
