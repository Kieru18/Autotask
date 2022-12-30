package z21.autotask.orm;

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
@Table(name = "species")
public class Species {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "species_seq")
    @SequenceGenerator(name = "species_seq", sequenceName = "species_seq", allocationSize = 1)
    @NonNull @Column(name = "species_id") private Integer speciesID;
    private String name;
    @Column(name = "food_type") private Date foodType;
    @Column(name = "average_lifespan") private Float averageLifespan;

}
