package pl.piotr.service.dto;

import lombok.*;
import pl.piotr.service.entity.Task;
import pl.piotr.service.entity.User;

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

    public static Function<CreateTaskRequest, Task> dtoToEntityMapper(Supplier<User> userSupplier) {
        return request -> Task.builder()
                .priority(request.getPriority())
                .description(request.getDescription())
                .extendedDescription(request.getExtendedDescription())
                .owner(userSupplier.get())
                .build();
    }

}
