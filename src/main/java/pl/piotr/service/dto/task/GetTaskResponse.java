package pl.piotr.service.dto.task;

import lombok.*;
import pl.piotr.service.entity.DateOfTask;
import pl.piotr.service.entity.Task;

import java.time.format.DateTimeFormatter;
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

    private String startDate;

    private String plannedEndDate;

    private String endDate;

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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return task -> GetTaskResponse.builder()
                .id(task.getId())
                .priority(task.getPriority())
                .description(task.getDescription())
                .extendedDescription(task.getExtendedDescription())
                .startDate(task.getStartDate() == null ? "null" : task.getStartDate().getTimestamp().format(formatter))
                .plannedEndDate(task.getPlanedEndDate() == null ? "null" : task.getPlanedEndDate().getTimestamp().format(formatter))
                .endDate(task.getEndDate() == null ? "null" : task.getEndDate().getTimestamp().format(formatter))
                .owner(User.builder()
                        .email(task.getOwner().getEmail())
                        .firstName(task.getOwner().getFirstName())
                        .lastName(task.getOwner().getLastName())
                        .build())
                .build();
    }
}
