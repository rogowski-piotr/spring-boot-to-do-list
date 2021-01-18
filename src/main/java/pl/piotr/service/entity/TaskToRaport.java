package pl.piotr.service.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "tasks_history")
public class TaskToRaport implements Serializable {

    @Id
    private int id;

    private Integer priority;

    private String description;

    @Column(name = "extended_description")
    private String extendedDescription;

    private String owner;

    @Column(name = "start_date_time")
    private LocalDateTime startDateTime;

    @Column(name = "end_date_time")
    private LocalDateTime endDateTime;

    @Column(name = "planned_date_time")
    private LocalDateTime plannedDateTime;

}