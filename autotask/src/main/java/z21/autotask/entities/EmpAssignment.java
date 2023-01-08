package z21.autotask.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;

@Data @Entity
@AllArgsConstructor @NoArgsConstructor
@Table(name = "emp_assignments")
public class EmpAssignment {
    @Id
    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id")
    private Integer employeeId;

    @Id
    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "task_id", referencedColumnName = "task_id")
    private Integer taskId;
}