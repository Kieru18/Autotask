package z21.autotask;

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
@Table(name = "animals")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "animals_seq")
    @SequenceGenerator(name = "animals_seq", sequenceName = "animals_seq", allocationSize = 1)
    @NonNull @Column(name = "animal_id") private Integer animalId;
    private String name;
    private String color;
    @Column(name = "leg_count") private Integer legCount;
}