package pl.piotr.service.dto.user;

import lombok.*;
import pl.piotr.service.entity.User;

import java.time.LocalDate;
import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateUserRequest {

    private String email;

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    public static BiFunction<User, UpdateUserRequest, User> dtoToEntityUpdater() {
        return (user, request) -> {
            user.setEmail(request.getEmail());
            user.setFirstName(request.getFirstName());
            user.setLastName(request.getLastName());
            user.setBirthDate(request.getBirthDate());
            return user;
        };
    }
}
