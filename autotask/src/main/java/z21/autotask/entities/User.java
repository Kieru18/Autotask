package z21.autotask.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.*;

@Data @Entity
@EqualsAndHashCode(exclude="employee")
@ToString(exclude = {"employee"})
@AllArgsConstructor @NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_seq")
    @SequenceGenerator(name = "users_seq", sequenceName = "users_seq", allocationSize = 1)
    @NonNull
    @Column(name = "user_id")
    private Integer userId;

    private String login;
    private String password;
    private String role;
    private String mail;

    @OneToOne(optional = true, mappedBy = "user")
    private Employee employee;

}
