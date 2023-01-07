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
@ToString(exclude = {"animals", "tasks"})
@AllArgsConstructor @NoArgsConstructor
@Table(name = "locations")
public class Location {

    @Id 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "locations_seq")
    @SequenceGenerator(name = "locations_seq", sequenceName = "locations_seq", allocationSize = 1)
    @NonNull 
    @Column(name = "location_id") 
    private Integer locationId;
    
    private String name;
    private Float latitude;
    private Float longitude;

    @OneToMany(mappedBy = "locationId")
    // @LazyCollection(LazyCollectionOption.FALSE)
    @Transient
    private List<Animal> animals;

    @OneToMany(mappedBy = "locationId", fetch = FetchType.LAZY)
    // @LazyCollection(LazyCollectionOption.FALSE)
    @Transient
    private List<Task> tasks;

}