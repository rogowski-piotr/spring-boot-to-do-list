package pl.piotr.service.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.piotr.service.entity.Task;
import pl.piotr.service.entity.User;
import pl.piotr.service.service.TaskService;
import pl.piotr.service.service.UserService;

import javax.annotation.PostConstruct;

@Component
public class InitializedData {

    private final UserService userService;
    private final TaskService taskService;

    @Autowired
    public InitializedData(UserService userService, TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }

    @PostConstruct
    private synchronized void init() {
        User president = User.builder()
                .email("donald@example.com")
                .build();

        User programmer = User.builder()
                .email("john@example.com")
                .build();

        userService.create(president);
        userService.create(programmer);

        Task task0 = Task.builder()
                .priority(1)
                .description("Deal with crisis")
                .extendedDescription("I don't know yet")
                .owner(president)
                .build();

        Task task1 = Task.builder()
                .priority(10)
                .description("Buy a new jet")
                .extendedDescription("buy the latest jet")
                .owner(president)
                .build();

        Task task2 = Task.builder()
                .priority(10)
                .description("extend the validity of the multisport card")
                .owner(programmer)
                .build();

        Task task3 = Task.builder()
                .priority(1)
                .description("create a new functionality")
                .extendedDescription("develop the most important app")
                .owner(programmer)
                .build();

        taskService.create(task0);
        taskService.create(task1);
        taskService.create(task2);
        taskService.create(task3);
    }

}
