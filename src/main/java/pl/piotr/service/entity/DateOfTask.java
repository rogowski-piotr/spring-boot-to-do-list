package pl.piotr.service.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "date")
public class DateOfTask implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "time_value")
    private LocalDateTime timestamp;

    @OneToOne(mappedBy = "startDate")
    private Task startTask;

    @OneToOne(mappedBy = "endDate")
    private Task endTask;

    @OneToOne(mappedBy = "planedEndDate")
    private Task planedEndDate;

}
