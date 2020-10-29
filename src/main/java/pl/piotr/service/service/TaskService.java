package pl.piotr.service.service;

import org.springframework.stereotype.Service;
import pl.piotr.service.entity.Task;
import pl.piotr.service.entity.User;
import pl.piotr.service.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> findAll(User user) {
        return repository.findAllByUser(user);
    }

    public Optional<Task> find(int id) {
        return repository.find(id);
    }

    public void create(Task task) {
        repository.create(task);
    }

    public void delete(Task task) {
        repository.delete(task);
    }

}
