package fr.groupeA.famillyBoard.services;

import fr.groupeA.famillyBoard.entities.Task;
import fr.groupeA.famillyBoard.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


  public List<Task> getTasks(){
        return taskRepository.findAll();
  }

  public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
  }

  public Task createTask(Task task) {
        return taskRepository.save(task);
  }

  public Task updateTask(Long id, Task task){
        Task taskTemp = taskRepository.findById(id).get();
        taskTemp.setTitle(task.getTitle());
        taskTemp.setDescription(task.getDescription());
        taskTemp.setStartDate(task.getStartDate());
        taskTemp.setEndDate(task.getEndDate());
        taskTemp.setStatus(task.getStatus());
        return taskRepository.save(taskTemp);
  }

  public void deleteTask(Long id) {
        taskRepository.deleteById(id);
  }
}
