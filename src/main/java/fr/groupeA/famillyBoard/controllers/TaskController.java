package fr.groupeA.famillyBoard.controllers;

import fr.groupeA.famillyBoard.entities.Task;
import fr.groupeA.famillyBoard.services.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping()
    public List<Task> getAllTasks() {
        return taskService.getTasks();
    }

    @GetMapping(path = "{taskId}")
    public Optional<Task> getTaskById(@PathVariable Long taskId){
        return taskService.getTaskById(taskId);
    }

    @GetMapping(path = "{familyMemberId}")
    public List<Task> getAllTasksByFamilyMember(@PathVariable Long familyMemberId, @RequestBody Task task) {
        return taskService.getTasksByFamilyMember(familyMemberId, task);
    }

    @PostMapping
    public Task addTask(@PathVariable Long familyMember, @RequestBody Task task){
        return taskService.createTask(familyMember, task);
    }

    @PutMapping(path = "{taskId}")
    public Task updateTask(@PathVariable Long taskId, @RequestBody Task task){
        return taskService.updateTask(taskId, task);
    }

    @DeleteMapping(path = "{taskId}")
    public void deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
    }
}
