package z21.autotask.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data @Entity
@AllArgsConstructor @NoArgsConstructor
@Table(name = "animals")
public class Animal {

    @Id 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "animals_seq")
    @SequenceGenerator(name = "animals_seq", sequenceName = "animals_seq", allocationSize = 1)
    @NonNull 
    @Column(name = "animal_id") 
    private Integer animalId;
    
    private String name;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "location_id", referencedColumnName = "location_id")
    private Location location;

    @ManyToOne(optional = false)
    @JoinColumn(name = "species_id", referencedColumnName = "species_id")
    private Species species;

    @Column(name = "birth_date") 
    private Date birthDate;

    private Float weight;    
}