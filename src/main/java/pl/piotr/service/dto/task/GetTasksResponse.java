package pl.piotr.service.dto.task;

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

    }

    @Singular
    private List<Task> tasks;

    public static Function <Collection<pl.piotr.service.entity.Task>, GetTasksResponse> entityToDtoMapper() {
        return tasks -> {
            GetTasksResponseBuilder response = GetTasksResponse.builder();
            tasks.stream()
                    .map(task -> Task.builder()
                            .id(task.getId())
                            .description(task.getDescription())
                            .build())
                    .forEach(response::task);
            return response.build();
        };
    }

}
