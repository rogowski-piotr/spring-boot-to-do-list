package pl.piotr.service.configuration;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.piotr.service.entity.DateOfTask;
import pl.piotr.service.entity.Task;
import pl.piotr.service.entity.User;
import pl.piotr.service.repository.DateOfTaskRepository;
import pl.piotr.service.service.TaskService;
import pl.piotr.service.service.UserService;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class InitializedData {

    private final UserService userService;
    private final TaskService taskService;
    private final DateOfTaskRepository dateOfTaskRepository;

    @Autowired
    public InitializedData(UserService userService, TaskService taskService, DateOfTaskRepository dateOfTaskRepository) {
        this.userService = userService;
        this.taskService = taskService;
        this.dateOfTaskRepository = dateOfTaskRepository;
    }

    @PostConstruct
    private synchronized void init() {
        User president = User.builder()
                .email("donald@example.com")
                .firstName("Donald")
                .lastName("Trump")
                .birthDate(LocalDate.of(1946,6,14))
                .password("donald123")
                .portrait(getResourceAsByteArray("avatar/dt.png"))
                .build();

        User programmer = User.builder()
                .email("john@example.com")
                .firstName("John")
                .lastName("Smith")
                .birthDate(LocalDate.of(2000,1,10))
                .password("veryComplicated")
                .portrait(getResourceAsByteArray("avatar/js.png"))
                .build();

        userService.create(president);
        userService.create(programmer);

        DateOfTask dateStart1 = DateOfTask.builder()
                .timestamp(LocalDateTime.now())
                .build();

        DateOfTask dateEnd1 = DateOfTask.builder()
                .timestamp(LocalDateTime.of(2021, 3, 1, 10, 0))
                .build();

        DateOfTask dateStart2 = DateOfTask.builder()
                .timestamp(LocalDateTime.now())
                .build();

        DateOfTask dateEnd2 = DateOfTask.builder()
                .timestamp(LocalDateTime.of(2021, 3, 1, 10, 0))
                .build();

        DateOfTask dateStart3 = DateOfTask.builder()
                .timestamp(LocalDateTime.now())
                .build();

        DateOfTask dateEnd3 = DateOfTask.builder()
                .timestamp(LocalDateTime.of(2021, 3, 1, 10, 0))
                .build();

        DateOfTask dateStart4 = DateOfTask.builder()
                .timestamp(LocalDateTime.now())
                .build();

        DateOfTask dateEnd4 = DateOfTask.builder()
                .timestamp(LocalDateTime.of(2021, 3, 1, 10, 0))
                .build();

        Task task0 = Task.builder()
                .priority(1)
                .startDate(dateStart1)
                .planedEndDate(dateEnd1)
                .description("Deal with crisis")
                .extendedDescription("I don't know yet")
                .owner(president)
                .build();

        Task task1 = Task.builder()
                .priority(10)
                .startDate(dateStart2)
                .planedEndDate(dateEnd2)
                .description("Buy a new jet")
                .extendedDescription("buy the latest jet")
                .owner(president)
                .build();

        Task task2 = Task.builder()
                .priority(10)
                .startDate(dateStart3)
                .planedEndDate(dateEnd3)
                .description("extend the validity of the multisport card")
                .owner(programmer)
                .build();

        Task task3 = Task.builder()
                .priority(1)
                .startDate(dateStart4)
                .planedEndDate(dateEnd4)
                .description("create a new functionality")
                .extendedDescription("develop the most important app")
                .owner(programmer)
                .build();

        dateOfTaskRepository.save(dateStart1);
        dateOfTaskRepository.save(dateEnd1);
        dateOfTaskRepository.save(dateStart2);
        dateOfTaskRepository.save(dateEnd2);
        dateOfTaskRepository.save(dateStart3);
        dateOfTaskRepository.save(dateEnd3);
        dateOfTaskRepository.save(dateStart4);
        dateOfTaskRepository.save(dateEnd4);
        taskService.create(task0);
        taskService.create(task1);
        taskService.create(task2);
        taskService.create(task3);
    }

    @SneakyThrows
    private byte[] getResourceAsByteArray(String name) {
        try (InputStream is = this.getClass().getResourceAsStream(name)) {
            return is.readAllBytes();
        }
    }

}
