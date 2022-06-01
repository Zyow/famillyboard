package fr.groupeA.famillyBoard.services;

import fr.groupeA.famillyBoard.entities.FamilyMember;
import fr.groupeA.famillyBoard.entities.Task;
import fr.groupeA.famillyBoard.repositories.FamilyMemberRepository;
import fr.groupeA.famillyBoard.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final FamilyMemberRepository familyMemberRepository;

    public TaskService(TaskRepository taskRepository, FamilyMemberRepository familyMemberRepository) {
        this.taskRepository = taskRepository;
        this.familyMemberRepository = familyMemberRepository;
    }

    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> getTasksByFamilyMember(Long familyMemberId, Task task) {
        familyMemberRepository.findById(familyMemberId);
        return taskRepository.findTasksByFamilyMember(task);
    }

    public Task updateTask(Long id, Task task) {
        Task taskTemp = taskRepository.findById(id).get();
        taskTemp.setTitle(task.getTitle());
        taskTemp.setDescription(task.getDescription());
        taskTemp.setStartDate(task.getStartDate());
        taskTemp.setEndDate(task.getEndDate());
        taskTemp.setStatus(task.getStatus());
        taskTemp.setFamilyMember(task.getFamilyMember());
        return taskRepository.save(taskTemp);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}