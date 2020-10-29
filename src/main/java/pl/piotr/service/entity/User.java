package pl.piotr.service.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class User implements Serializable {

    private String email;

    @ToString.Exclude
    private String password;

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    private List<Task> toDoList;

}
