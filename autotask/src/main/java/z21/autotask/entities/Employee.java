package z21.autotask.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.*;

@Data @Entity
@ToString(exclude = {"user"})
@AllArgsConstructor @NoArgsConstructor
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employees_seq")
    @SequenceGenerator(name = "employees_seq", sequenceName = "employees_seq", allocationSize = 1)
    @NonNull
    @Column(name = "employee_id")
    private Integer employeeId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private char gender;

    @Column(name = "birth_date")
    private Date birthDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "position_id", referencedColumnName = "position_id")
    private Position position;

    @ManyToOne(optional = false)
    @JoinColumn(name = "status_id", referencedColumnName = "status_id")
    private EmpStatus status;

    @OneToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    public String getFullName() {
       String fName = this.firstName + " " + this.lastName;
       return fName;
    }

    public String getPictureUrl() {
        return "src/main/resources/images" + this.position.getPhoto();
    }

    public String getProfession() {
        return this.position.getName();
    }

    public String getEmail() {
        return this.user.getMail();
    }
}
