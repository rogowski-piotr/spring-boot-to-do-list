package pl.piotr.service.repository;

import org.springframework.beans.factory.annotation.Autowired;
import pl.piotr.service.data.DataStorage;
import pl.piotr.service.entity.Task;
import pl.piotr.service.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@org.springframework.stereotype.Repository
public class TaskRepository implements Repository<Task, Integer> {

    private DataStorage dataStorage;

    @Autowired
    public TaskRepository(DataStorage dataStorage) {
        this.dataStorage = dataStorage;
    }

    @Override
    public Optional<Task> find(Integer id) {
        return dataStorage.findTask(id);
    }

    @Override
    public List<Task> findAll() {
        return dataStorage.getAllTasks();
    }

    @Override
    public void create(Task entity) {
        dataStorage.createTask(entity);
    }

    @Override
    public void delete(Task entity) {
        dataStorage.deleteTask(entity.getId());
    }

    @Override
    public void update(Task entity) {
        dataStorage.updateTask(entity);
    }

    public List<Task> findAllByUser(User user) {
        return dataStorage.getTaskStream()
                .filter(task -> task.getOwner().equals(user))
                .collect(Collectors.toList());
    }
}
