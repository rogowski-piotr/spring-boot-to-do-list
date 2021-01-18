package pl.piotr.service.dto.raport;

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
public class GetTasksRaportResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @ToString
    @EqualsAndHashCode
    public static class TaskToReport {

        private int id;

        private String description;

        private String ownerEmail;

        private String startDate;

        private String endDate;

        private String plannedEndDate;
    }

    @Singular
    private List<TaskToReport> tasks;

    public static Function <Collection<pl.piotr.service.entity.TaskToRaport>, GetTasksRaportResponse> entityToDtoMapper() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return taskToRaports -> {
            GetTasksRaportResponseBuilder response = GetTasksRaportResponse.builder();
            taskToRaports.stream()
                    .map(taskToRaport -> TaskToReport.builder()
                            .id(taskToRaport.getId())
                            .description(taskToRaport.getDescription())
                            .ownerEmail(taskToRaport.getOwner())
                            .startDate(taskToRaport.getStartDateTime() == null ? null : taskToRaport.getStartDateTime().format(formatter))
                            .endDate(taskToRaport.getEndDateTime() == null ? null : taskToRaport.getEndDateTime().format(formatter))
                            .plannedEndDate(taskToRaport.getPlannedDateTime() == null ? null : taskToRaport.getPlannedDateTime().format(formatter))
                            .build())
                    .forEach(response::task);
            return response.build();
        };
    }

}
