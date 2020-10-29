package pl.piotr.service.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class Task implements Serializable {

    private int id;

    private Integer priority;

    private String description;

    private String extendedDescription;

    private User owner;

}
