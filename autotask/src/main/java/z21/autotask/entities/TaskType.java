package z21.autotask.entities;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data @Entity
@AllArgsConstructor @NoArgsConstructor
@Table(name = "task_types")
public class TaskType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_types_seq")
    @SequenceGenerator(name = "task_types_seq", sequenceName = "task_types_seq", allocationSize = 1)
    @NonNull
    @Column(name = "type_id")
    private Integer typeId;

    private String name;

    private String description;

    @Column(name = "base_priority")
    private Integer basePriority;

    private Date frequency;

}
