package pl.piotr.service.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.piotr.service.entity.User;
import pl.piotr.service.service.UserService;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Component
public class InitializedData {

    private final UserService userService;

    @Autowired
    public InitializedData(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    private synchronized void init() {
        User president = User.builder()
                .email("donald@example.com")
                .firstName("Donald")
                .lastName("Trump")
                .birthDate(LocalDate.of(1946,6,14))
                .password("donald123")
                .build();

        User programmer = User.builder()
                .email("john@example.com")
                .firstName("John")
                .lastName("Smith")
                .birthDate(LocalDate.of(2000,1,10))
                .password("veryComplicated")
                .build();

        userService.create(president);
        userService.create(programmer);
    }

}
