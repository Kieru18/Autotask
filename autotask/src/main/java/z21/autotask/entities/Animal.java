package z21.autotask.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    private Float weight;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "location_id", referencedColumnName = "location_id")
    private Location location;

    @ManyToOne(optional = false)
    @JoinColumn(name = "species_id", referencedColumnName = "species_id")
    private Species species;

    @Column(name = "birth_date") 
    private Date birthDate;

    // @ManyToMany(fetch = FetchType.LAZY,
    //         cascade = {
    //             CascadeType.PERSIST,
    //             CascadeType.MERGE
    //         },
    //         mappedBy = "animals")
    // @JsonIgnore
    // private Set<Task> tasks = new HashSet<>();

}