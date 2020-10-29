package pl.piotr.service.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
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
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    private String email;

    @ToString.Exclude
    private String password;

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Task> toDoList;

}
