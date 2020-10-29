package pl.piotr.service.dto.task;

import lombok.*;
import pl.piotr.service.entity.Task;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetTaskResponse {

    private int id;

    private Integer priority;

    private String description;

    private String extendedDescription;

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

    private User owner;

    public static Function<Task, GetTaskResponse> entityToDtoMapper() {
        return task -> GetTaskResponse.builder()
                .id(task.getId())
                .priority(task.getPriority())
                .description(task.getDescription())
                .extendedDescription(task.getExtendedDescription())
                .owner(User.builder()
                        .email(task.getOwner().getEmail())
                        .firstName(task.getOwner().getFirstName())
                        .lastName(task.getOwner().getLastName())
                        .build())
                .build();
    }
}
