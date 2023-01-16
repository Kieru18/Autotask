package z21.autotask.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;

@Data @Entity
@IdClass(EmpAssignmentId.class)
@AllArgsConstructor @NoArgsConstructor
@Table(name = "emp_assignments")
public class EmpAssignment {
    @Id
    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id")
    private Employee employee;

    @Id
    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "task_id", referencedColumnName = "task_id")
    private Task task;
}

@EqualsAndHashCode
class EmpAssignmentId implements Serializable {
    private Employee employee;
    private Task task;
}
