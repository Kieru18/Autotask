package z21.autotask;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "animals")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NonNull private Integer animal_id;
    private String name;
    private String color;
    private Integer leg_count;

    @Override
    public String toString() {
        return String.format(
            "Animal[animalId=%d, name='%s', color='%s', legCount=%d]",
            animal_id, name, color, leg_count);
    }
}
