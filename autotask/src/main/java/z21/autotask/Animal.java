package z21.autotask;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull private Integer animal_id;
    private String name;
    private String color;
    private Integer leg_count;

}
