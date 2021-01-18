package pl.piotr.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.piotr.service.entity.DateOfTask;
import pl.piotr.service.entity.Task;
import pl.piotr.service.entity.TaskToRaport;
import pl.piotr.service.entity.User;
import pl.piotr.service.repository.DateOfTaskRepository;
import pl.piotr.service.repository.TaskRepository;
import pl.piotr.service.repository.TaskToRaportRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private TaskRepository repository;

    private DateOfTaskRepository dateRepository;

    @Autowired
    public TaskService(TaskRepository repository, DateOfTaskRepository dateRepository) {
        this.repository = repository;
        this.dateRepository = dateRepository;
    }

    public List<Task> findAll(User user) {
        return repository.findAllByOwner(user);
    }

    public Optional<Task> find(int id) {
        return repository.findById(id);
    }

    @Transactional
    public Task create(Task task) {
        dateRepository.save(task.getStartDate());
        dateRepository.save(task.getPlanedEndDate());
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
