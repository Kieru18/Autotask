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
@ToString(exclude = "employees")
@AllArgsConstructor @NoArgsConstructor
@Table(name = "positions")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "positions_seq")
    @SequenceGenerator(name = "positions_seq", sequenceName = "positions_seq", allocationSize = 1)
    @NonNull
    @Column(name = "position_id")
    private Integer positionId;

    private String name;
    private String photo;
    
    private String photo;


}
