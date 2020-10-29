package pl.piotr.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pl.piotr.service.dto.task.CreateTaskRequest;
import pl.piotr.service.dto.task.GetTaskResponse;
import pl.piotr.service.dto.task.GetTasksResponse;
import pl.piotr.service.dto.task.UpdateTaskRequest;
import pl.piotr.service.entity.Task;
import pl.piotr.service.entity.User;
import pl.piotr.service.service.TaskService;
import pl.piotr.service.service.UserService;

import java.util.Optional;

@RestController
@RequestMapping("api/users/{username}/tasks")
public class TaskController {

    private TaskService taskService;

    private UserService userService;

    @Autowired
    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    @GetMapping()
    public ResponseEntity<GetTasksResponse> getTasks(@PathVariable("username") String username) {
        Optional<User> user = userService.find(username);
        return user.map(value -> ResponseEntity.ok(GetTasksResponse.entityToDtoMapper().apply(taskService.findAll(value))))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("{id}")
    public ResponseEntity<GetTaskResponse> getTask(@PathVariable("username") String username,
                                                   @PathVariable("id") int id) {
        Optional<User> user = userService.find(username);
        if (user.isPresent()) {
            Optional<Task> task = taskService.find(id);
            return task.map(t -> ResponseEntity.ok(GetTaskResponse.entityToDtoMapper().apply(t)))
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> createTask(@PathVariable("username") String username,
                                           @RequestBody CreateTaskRequest request,
                                           UriComponentsBuilder builder) {
        Optional<User> user = userService.find(username);
        if (user.isPresent()) {
            Task task = CreateTaskRequest
                    .dtoToEntityMapper(user::get)
                    .apply(request);
            task = taskService.create(task);
            return ResponseEntity.created(builder.pathSegment("api", "users", "{username}", "tasks", "{id}")
                    .buildAndExpand(user.get().getEmail(), task.getId()).toUri()).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateTask(@RequestBody UpdateTaskRequest request, @PathVariable("id") int id) {
        Optional<Task> task = taskService.find(id);
        if (task.isPresent()) {
            UpdateTaskRequest.dtoToEntityUpdater().apply(task.get(), request);
            taskService.update(task.get());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable("id") int id) {
        Optional<Task> task = taskService.find(id);
        if (task.isPresent()) {
            taskService.delete(task.get().getId());
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
