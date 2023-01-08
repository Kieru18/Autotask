package z21.autotask.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data @Entity
@AllArgsConstructor @NoArgsConstructor
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tasks_seq")
    @SequenceGenerator(name = "tasks_seq", sequenceName = "tasks_seq", allocationSize = 1)
    @NonNull
    @Column(name = "task_id")
    private Integer taskId;

    private String description;

    @Column(name = "date_start")
    private Date dateStart;

    @Column(name = "date_end")
    private Date dateEnd;

    private Date deadline;
    
    private Integer priority;

    @ManyToOne(optional = false)
    @JoinColumn(name = "location_id", referencedColumnName = "location_id")
    private Location location;

    @ManyToOne(optional = false)
    @JoinColumn(name = "status_id", referencedColumnName = "status_id")
    private TaskStatus status; 

    @ManyToOne(optional = false)
    @JoinColumn(name = "type_id", referencedColumnName = "type_id")
    private TaskType type;

    // @ManyToMany(fetch = FetchType.LAZY,
    //         cascade = {
    //             CascadeType.PERSIST,
    //             CascadeType.MERGE
    //         })
    // @JoinTable(name = "animal_assignments",
    //            joinColumns = { @JoinColumn(name = "task_id") },
    //            inverseJoinColumns = { @JoinColumn(name = "animal_id") })
    // private Set<Animal> animals = new HashSet<>();
}
