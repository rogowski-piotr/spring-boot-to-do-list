package pl.piotr.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.piotr.service.entity.DateOfTask;

public interface DateOfTaskRepository extends JpaRepository<DateOfTask, Integer> {
}
