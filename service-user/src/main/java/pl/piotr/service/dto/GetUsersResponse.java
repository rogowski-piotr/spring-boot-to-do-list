package pl.piotr.service.dto;

import lombok.*;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetUsersResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class User {

        private String email;

        private String firstName;

        private String lastName;

    }

    @Singular
    private List<User> users;

    public static Function <Collection<pl.piotr.service.entity.User>, GetUsersResponse> entityToDtoMapper() {
        return users -> {
            GetUsersResponseBuilder response = GetUsersResponse.builder();
            users.stream()
                    .map(user -> User.builder()
                            .email(user.getEmail())
                            .firstName(user.getFirstName())
                            .lastName(user.getLastName())
                            .build())
                    .forEach(response::user);
            return response.build();
        };
    }

}
