package pl.piotr.service.dto.task;

import lombok.*;
import pl.piotr.service.entity.Task;

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateTaskRequest {

    private Integer priority;

    private String description;

    private String extendedDescription;

    public static BiFunction<Task, UpdateTaskRequest, Task> dtoToEntityUpdater() {
        return (task, request) -> {
            task.setPriority(request.getPriority());
            task.setDescription(request.getDescription());
            task.setExtendedDescription(request.getExtendedDescription());
            return task;
        };
    }

}
