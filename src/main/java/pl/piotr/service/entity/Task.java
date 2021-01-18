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
@Table(name = "tasks")
public class Task implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Integer priority;

    private String description;

    @Column(name = "extended_description")
    private String extendedDescription;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "owner")
    private User owner;

    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "start_date", referencedColumnName = "id")
    private DateOfTask startDate;

    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "end_date", referencedColumnName = "id")
    private DateOfTask endDate;

    @ToString.Exclude
    @OneToOne
    @JoinColumn(name = "planed_end_date", referencedColumnName = "id")
    private DateOfTask planedEndDate;

}
