package pl.piotr.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.piotr.service.entity.TaskToRaport;

public interface TaskToRaportRepository extends JpaRepository<TaskToRaport, Integer> {
}
