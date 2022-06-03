package fr.groupeA.famillyBoard.repositories;

import fr.groupeA.famillyBoard.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findTaskByFamilyMember_Id(Long id);
}