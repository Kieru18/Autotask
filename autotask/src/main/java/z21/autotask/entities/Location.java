package z21.autotask.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data @Entity
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
    private List<Animal> animals;

}