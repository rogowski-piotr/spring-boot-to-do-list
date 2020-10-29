package pl.piotr.service.data;

import org.springframework.stereotype.Component;
import pl.piotr.service.entity.Task;
import pl.piotr.service.entity.User;

import java.util.*;
import java.util.stream.Stream;

@Component
public class DataStorage {

    private Set<User> users = new HashSet<>();
    private Set<Task> tasks = new HashSet<>();

    public synchronized List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    public Optional<User> findUser(String email) {
        return users.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst();
    }

    public synchronized void createUser(User user) throws IllegalArgumentException {
        findUser(user.getEmail()).ifPresentOrElse(
                user1 -> { throw new IllegalArgumentException("User with this email already exist " + user.getEmail());
                },
                () -> users.add(user));
    }

    public synchronized List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    public Optional<Task> findTask(int id) {
        return tasks.stream()
                .filter(task -> task.getId() == id)
                .findFirst();
    }

    public synchronized void createTask(Task task) throws IllegalArgumentException {
        task.setId(getAllTasks().stream().mapToInt(Task::getId).max().orElse(0) + 1);
        tasks.add(task);
    }

    public synchronized void updateTask(Task task) throws IllegalArgumentException {
        findTask(task.getId()).ifPresentOrElse(
                task1 -> {
                    tasks.remove(task1);
                    tasks.add(task);
                },
                () -> {
                    throw new IllegalArgumentException("Task with this id does not exist " + task.getId());
                });
    }

    public synchronized void deleteTask(int id) throws IllegalArgumentException {
        findTask(id).ifPresentOrElse(
                task -> tasks.remove(task),
                () -> {
                    throw new IllegalArgumentException("Task with this id does not exist " + id);
                });
    }

    public Stream<Task> getTaskStream() {
        return tasks.stream();
    }

}
