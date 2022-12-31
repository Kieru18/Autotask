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
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data @Entity
@AllArgsConstructor @NoArgsConstructor
@Table(name = "species")
public class Species {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "species_seq")
    @SequenceGenerator(name = "species_seq", sequenceName = "species_seq", allocationSize = 1)
    @NonNull 
    @Column(name = "species_id") 
    private Integer speciesId;

    private String name;

    @Column(name = "food_type") 
    private String foodType;

    @Column(name = "average_lifespan") 
    private Integer averageLifespan;

    @OneToMany(mappedBy = "speciesId")
    @LazyCollection(LazyCollectionOption.FALSE)
    @Transient
    private List<Animal> animals;
}
