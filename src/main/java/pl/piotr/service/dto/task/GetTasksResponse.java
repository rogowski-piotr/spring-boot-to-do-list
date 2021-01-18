package pl.piotr.service.dto.task;

import lombok.*;

import java.time.format.DateTimeFormatter;
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
public class GetTasksResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class Task {

        private int id;

        private String description;

        private String startDate;

        private String plannedEndDate;
    }

    @Singular
    private List<Task> tasks;

    public static Function <Collection<pl.piotr.service.entity.Task>, GetTasksResponse> entityToDtoMapper() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return tasks -> {
            GetTasksResponseBuilder response = GetTasksResponse.builder();
            tasks.stream()
                    .map(task -> Task.builder()
                            .id(task.getId())
                            .description(task.getDescription())
                            .startDate(task.getStartDate() == null ? "null" : task.getStartDate().getTimestamp().format(formatter))
                            .plannedEndDate(task.getPlanedEndDate() == null ? "null" : task.getPlanedEndDate().getTimestamp().format(formatter))
                            .build())
                    .forEach(response::task);
            return response.build();
        };
    }

}
