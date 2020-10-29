package pl.piotr.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.piotr.service.entity.Task;
import pl.piotr.service.entity.User;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    List<Task> findAllByOwner(User user);



}
