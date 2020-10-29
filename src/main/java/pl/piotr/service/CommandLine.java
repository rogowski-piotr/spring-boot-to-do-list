package pl.piotr.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.piotr.service.entity.Task;
import pl.piotr.service.entity.User;
import pl.piotr.service.service.TaskService;
import pl.piotr.service.service.UserService;

import java.util.List;
import java.util.Scanner;

@Component
public class CommandLine implements CommandLineRunner {

    private static final String MENU = "\nAvailable commands:\n" +
            "1. List all task for one user\n" +
            "2. List all avaible users\n" +
            "3. Add new task\n" +
            "4. Delete task\n" +
            "5. Add new user\n" +
            "6. Stop the application";
    private UserService userService;
    private TaskService taskService;

    public CommandLine(UserService userService, TaskService taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }

    @Override

    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;

        while (isRunning) {
            System.out.println(MENU);
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    System.out.println("Input the email:");
                    String email = scanner.nextLine();

                    if (userService.find(email).isPresent()) {
                        taskService.findAll(userService.find(email).get())
                                .forEach(System.out::println);
                    }
                    break;

                case "2":
                    userService.findAll().forEach(System.out::println);
                    break;

                case "3":
                    try {
                        System.out.println("email:");
                        String ownerEmail = scanner.nextLine();
                        System.out.println("priority:");
                        int priority = Integer.valueOf(scanner.nextLine());
                        System.out.println("description:");
                        String description = scanner.nextLine();
                        System.out.println("extendedDescription:");
                        String extendedDescription = scanner.nextLine();

                        if (userService.find(ownerEmail).isPresent()) {
                            User owner = userService.find(ownerEmail).get();
                            Task task = new Task();
                            task.setOwner(owner);
                            task.setPriority(priority);
                            task.setDescription(description);
                            task.setExtendedDescription(extendedDescription);
                            taskService.create(task);
                            System.out.println("task added successfully");
                        } else {
                            System.out.println("Incorrect email input");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Incorrect priority input");
                    }
                    break;

                case "4":
                    System.out.println("Input the email:");
                    String emailInput = scanner.nextLine();

                    if (! userService.find(emailInput).isPresent()) {
                        System.out.println("Incorrect email input");
                        break;
                    }
                    List<Task> tasks = taskService.findAll(userService.find(emailInput).get());
                    if (tasks.size() == 0) {
                        System.out.println("This user has any tasks");
                        break;
                    }

                    int counter = 0;
                    System.out.println("select task to remove");
                    for (Task i : tasks) {
                        System.out.println(counter++ + " " + i);
                    }

                    try {
                        int chosenTask = Integer.valueOf(scanner.nextLine());
                        int id = tasks.get(chosenTask).getId();
                        taskService.delete(taskService.find(id).get());
                        System.out.println("Task removed successfully");
                    } catch (Exception e) {
                        System.out.println("Incorrect input");
                    }
                    break;

                case "5":
                    System.out.println("email:");
                    String ownerEmail = scanner.nextLine();
                    System.out.println("first name:");
                    String firstName = scanner.nextLine();
                    System.out.println("last name:");
                    String lastName = scanner.nextLine();
                    System.out.println("password:");
                    String password = scanner.nextLine();

                    User user = new User();
                    user.setEmail(ownerEmail);
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setPassword(password);

                    userService.create(user);
                    break;

                case "6":
                    isRunning = false;
                    break;

                default:
                    System.out.println("Unsupported option");
                    break;
            }
        }
    }

}
