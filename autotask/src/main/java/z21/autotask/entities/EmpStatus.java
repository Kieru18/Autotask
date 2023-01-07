package z21.autotask.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@Data @Entity
@AllArgsConstructor @NoArgsConstructor
@Table(name = "emp_status")
public class EmpStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emp_status_seq")
    @SequenceGenerator(name = "emp_status_seq", sequenceName = "emp_status_seq", allocationSize = 1)
    @NonNull
    @Column(name = "status_id")
    private Integer statusId;

    private String description;

}
