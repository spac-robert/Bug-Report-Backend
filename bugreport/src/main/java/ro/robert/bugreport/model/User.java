package ro.robert.bugreport.model;

import lombok.*;
import ro.robert.bugreport.configuration.Role;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "user", schema = "public")
public class User {
    @Id
    @GeneratedValue()
    private Integer id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
