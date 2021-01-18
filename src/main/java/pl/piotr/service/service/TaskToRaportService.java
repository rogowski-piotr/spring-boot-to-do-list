package pl.piotr.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.piotr.service.entity.TaskToRaport;
import pl.piotr.service.repository.TaskToRaportRepository;

import java.util.List;

@Service
public class TaskToRaportService {

    private TaskToRaportRepository repository;

    @Autowired
    public TaskToRaportService(TaskToRaportRepository repository) {
        this.repository = repository;
    }

    public List<TaskToRaport> findAll() {
        return repository.findAll();
    }

}
