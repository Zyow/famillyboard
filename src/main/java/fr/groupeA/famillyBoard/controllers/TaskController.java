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

    @CrossOrigin(origins = "/*")
    @GetMapping()
    public List<Task> getAllTasks() {
        return taskService.getTasks();
    }

    @CrossOrigin(origins = "/*")
    @GetMapping(path = "{taskId}")
    public Optional<Task> getTaskById(@PathVariable Long taskId){
        return taskService.getTaskById(taskId);
    }

    @CrossOrigin(origins = "/*")
    @GetMapping(path = "{familyMemberId}")
    public List<Task> getAllTasksByFamilyMember(@PathVariable Long familyMemberId, @RequestBody Task task) {
        return taskService.getTasksByFamilyMember(familyMemberId, task);
    }

    @CrossOrigin(origins = "/*")
    @PostMapping
    public Task addTask(@RequestBody Task task){
        return taskService.createTask(task);
    }

    @CrossOrigin(origins = "/*")
    @PutMapping(path = "{taskId}")
    public Task updateTask(@PathVariable Long taskId, @RequestBody Task task){
        return taskService.updateTask(taskId, task);
    }

    @CrossOrigin(origins = "/*")
    @DeleteMapping(path = "{taskId}")
    public void deleteTask(@PathVariable Long taskId) {
        taskService.deleteTask(taskId);
    }
}
