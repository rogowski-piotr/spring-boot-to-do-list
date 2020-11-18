package pl.piotr.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.piotr.service.entity.Task;
import pl.piotr.service.entity.User;
import pl.piotr.service.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private TaskRepository repository;

    @Autowired
    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> findAll(User user) {
        return repository.findAllByOwner(user);
    }

    public Optional<Task> find(int id) {
        return repository.findById(id);
    }

    @Transactional
    public Task create(Task task) {
        return repository.save(task);
    }

    @Transactional
    public void update(Task task) {
        repository.save(task);
    }

    @Transactional
    public void delete(int id) {
        repository.deleteById(id);
    }

}
