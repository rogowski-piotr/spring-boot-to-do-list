package pl.piotr.service.dto.task;

import lombok.*;
import pl.piotr.service.entity.DateOfTask;
import pl.piotr.service.entity.Task;
import pl.piotr.service.entity.User;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Function;
import java.util.function.Supplier;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateTaskRequest {

    private Integer priority;

    private String description;

    private String extendedDescription;

    private User owner;

    private String startDate;

    private String plannedEndDate;

    public static Function<CreateTaskRequest, Task> dtoToEntityMapper(Supplier<User> userSupplier) {
        return request -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            DateOfTask startDate = DateOfTask.builder()
                    .timestamp(LocalDateTime.parse(request.startDate, formatter))
                    .build();
            DateOfTask plannedEndDate = DateOfTask.builder()
                    .timestamp(LocalDateTime.parse(request.plannedEndDate, formatter))
                    .build();
            return Task.builder()
                    .priority(request.getPriority())
                    .description(request.getDescription())
                    .extendedDescription(request.getExtendedDescription())
                    .owner(userSupplier.get())
                    .startDate(startDate)
                    .planedEndDate(plannedEndDate)
                    .build();
        };
    }

}
