package z21.autotask.entities;

import lombok.Data;
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
@IdClass(AnimalAssignmentId.class)
@AllArgsConstructor @NoArgsConstructor
@Table(name = "animal_assignments")
public class AnimalAssignment {
    @Id
    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "animal_id", referencedColumnName = "animal_id")
    private Animal animal;

    @Id
    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "task_id", referencedColumnName = "task_id")
    private Task task;
}

class AnimalAssignmentId implements Serializable {
    private Animal animal;
    private Task task;
}

